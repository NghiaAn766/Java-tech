package com.example.ex6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // Read a list of students whose age is greater than or equal to x
    public Iterable<Student> getStudentsByAge(int x) {
        return studentRepository.searchByAge(x);
    }

    // Count the number of students whose ielts score is equal to x
    public long countStudentsByIeltsScore(double x) {
        return studentRepository.searchByIeltsScore(x).size();
    }

    // Find the list of students whose name contains the word xxx (case-insensitive)
    public Iterable<Student> findStudentsByName(String keyword) {
        return studentRepository.searchByName(keyword.toLowerCase());
    }

    public Iterable<Student> getStudentsSorted() {
        Sort customSort = Sort.by(
                Sort.Order.desc("age"),
                Sort.Order.asc("ieltsScore")
        );
        return studentRepository.findAll(customSort);
    }

    public Page<Student> getStudentsPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return studentRepository.findByAgeGreaterThanEqual(0, pageable);
    }

}
