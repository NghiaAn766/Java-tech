package com.example.ex3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Ex3Application implements CommandLineRunner {

	@Autowired
	private StudentManager studentManager;

	public static void main(String[] args) {
		SpringApplication.run(Ex3Application.class, args);
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
	}
}
