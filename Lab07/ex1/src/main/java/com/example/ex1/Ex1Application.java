package com.example.ex1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Ex1Application.class, args);
	}

	@java.lang.Override
	public void run(java.lang.String... args) throws Exception {
		System.out.println("Môn học công nghệ Java");
	}
}
