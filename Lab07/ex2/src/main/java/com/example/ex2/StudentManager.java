package com.example.ex2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentManager implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(long id) throws Exception {
        return studentRepository.findById(id)
                .orElseThrow(() -> new Exception("Student not found"));
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(long id) {
        studentRepository.deleteById(id);
    }
}
