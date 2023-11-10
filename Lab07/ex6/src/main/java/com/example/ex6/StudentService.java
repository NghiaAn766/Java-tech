package com.example.ex6;

import org.springframework.stereotype.Repository;

@Repository
public interface StudentService {
    Iterable<Student> getAllStudents();

    Student getStudent(long id) throws Exception;

    void save(Student student);

    void delete(long id);
}
