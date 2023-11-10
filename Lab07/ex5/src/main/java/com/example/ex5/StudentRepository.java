package com.example.ex5;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.age >= :age")
    public Collection<Student> searchByAge(@Param("age") Integer age);

    @Query("SELECT s FROM Student s WHERE s.ieltsScore = :ieltsScore")
    public List<Student> searchByIeltsScore(@Param("ieltsScore") Double ieltsScore);

    @Query("SELECT s FROM Student s WHERE lower(s.name) like %:keyword%")
    public List<Student> searchByName(@Param("keyword") String keyword);
}
