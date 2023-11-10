package com.example.ex4;

import org.springframework.stereotype.Repository;

@Repository
public interface StudentService {
    public Iterable<Student> getAllStudents();

    public Student getStudent(long id) throws Exception;

    public void save(Student student);

    public  void delete(long id);
}
