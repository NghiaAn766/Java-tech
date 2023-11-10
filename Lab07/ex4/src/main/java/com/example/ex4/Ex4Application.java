package com.example.ex4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Ex4Application implements CommandLineRunner {

	@Autowired
	private StudentManager studentManager;

	public static void main(String[] args) {
		SpringApplication.run(Ex4Application.class, args);
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
		showStudents();

		Student student1 = this.studentManager.getStudent(1);
		student1.setIeltsScore(7.5);
		System.out.println("After updating students");
		this.studentManager.save(student1);
		showStudents();

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
