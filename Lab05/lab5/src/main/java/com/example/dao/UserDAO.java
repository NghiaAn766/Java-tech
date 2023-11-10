package com.example.dao;

import com.example.model.User;
import com.example.repository.Repository;
import com.example.utils.HibernateUtils;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAO implements Repository<User> {
    private final SessionFactory factory = HibernateUtils.getSessionFactory();

    @Override
    public boolean add(User user) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public User get(int id) {
        Session session = factory.openSession();
        try {
            return session.get(User.class, id);
        } finally {
            session.close();
        }
    }

    public User getUserByEmail(String email) {
        Session session = factory.openSession();

        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);

            cq.select(root).where(cb.equal(root.get("email"), email));

            TypedQuery<User> query = session.createQuery(cq);
            User user = query.getSingleResult();
            if (user != null) {
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public boolean remove(User user) {
        return false;
    }

}