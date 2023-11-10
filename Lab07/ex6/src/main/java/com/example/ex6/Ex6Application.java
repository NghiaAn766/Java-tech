package com.example.ex6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class Ex6Application implements CommandLineRunner {

    @Autowired
    private StudentManager studentManager;

    public static void main(String[] args) {
        SpringApplication.run(Ex6Application.class, args);
    }

    private void showStudents(){
        List<Student> studentList = (List<Student>) this.studentManager.getAllStudents();
        for (Student student : studentList){
            System.out.println(student);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        this.studentManager.save(new Student(1, "Ruan Mei", 18, "acer@gmail.com", 6.5));
        this.studentManager.save(new Student(2, "Dr Ratio", 20, "ratio@gmail.com", 8.5));
        this.studentManager.save(new Student(3, "Topaz", 19, "numpy@gmail.com", 8.0));
        this.studentManager.save(new Student(4, "Seele", 20, "seele@gmail.com", 7.0));
        this.studentManager.save(new Student(5, "Gepard", 22, "gepard@gmail.com", 7.5));
        this.studentManager.save(new Student(6, "Blade", 21, "blade@gmail.com", 8.0));
        this.studentManager.save(new Student(7, "Jack", 20, "jack@gmail.com", 6.5));
        this.studentManager.save(new Student(8, "Bamboo", 19, "bamboo@gmail.com", 6.0));
        this.studentManager.save(new Student(9, "Ken", 20, "ken@gmail.com", 7.0));
        this.studentManager.save(new Student(10, "Susang", 18, "susang@gmail.com", 7.5));
        showStudents();

        Student student1 = this.studentManager.getStudent(1);
        student1.setIeltsScore(7.5);
        System.out.println("After updating students");
        this.studentManager.save(student1);
        showStudents();

        System.out.println("Read the list of students, sorted in descending order by age");
        for(Student s : studentManager.getStudentsSorted()){
            System.out.println(s);
        }

        System.out.println("Read students 4-5-6 and print them to the console");
        int page = 2;
        int size = 3;
        Page<Student> studentsPage = studentManager.getStudentsPaged(page, size);

        studentsPage.getContent().forEach(System.out::println);

        this.studentManager.delete(1);
        System.out.println("After deleting students");
        showStudents();

        System.out.println("Read a list of students whose age is greater than or equal to x");
        for(Student s : studentManager.getStudentsByAge(17)){
            System.out.println(s);
        }

        System.out.println("Count the number of students whose ielts score is equal to 7.5 = " + studentManager.countStudentsByIeltsScore(7.5));

        System.out.println("Find the list of students whose name contains the word xxx (case-insensitive)");
        for(Student s : studentManager.findStudentsByName("T")){
            System.out.println(s);
        }

    }
}
