package com.example.dao;
import com.example.model.Product;

import com.example.repository.Repository;
import com.example.utils.HibernateUtils;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAO implements Repository<Product>{
    private final SessionFactory factory = HibernateUtils.getSessionFactory();

    @Override
    public boolean add(Product product) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(product);
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
    public Product get(int id) {
        Session session = factory.openSession();
        try {
            return session.get(Product.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Product> getAll() {
        Session session = factory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        cq.select(root);

        try {
            Query query = session.createQuery(cq);
            return (List<Product>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Example: print the stack trace
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean remove(int id) {
        Session session = factory.openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean remove(Product product) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
