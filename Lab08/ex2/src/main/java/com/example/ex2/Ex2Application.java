package com.example.ex2;

import com.example.ex2.model.Employee;
import com.example.ex2.service.EmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex2Application implements CommandLineRunner {
	@Autowired
	private EmployeeManager employeeManager;

	public static void main(String[] args) {
		SpringApplication.run(Ex2Application.class, args);
	}

	public void init(){
		employeeManager.save(new Employee("Thomas Hardy", "thomashardy@mail.com", "89 Chiaroscuro Rd, Portland, USA", "(171) 555-2222"));
		employeeManager.save(new Employee("Dominique Perrier", "dominiqueperrier@mail.com", "Obere Str. 57, Berlin, Germany", "(313) 555-5735"));
		employeeManager.save(new Employee("Maria Anders", "mariaanders@mail.com", "25, rue Lauriston, Paris, France", "(503) 555-9931"));
		employeeManager.save(new Employee("Fran Wilson", "franwilson@mail.com", "C/ Araquil, 67, Madrid, Spain", "(204) 619-5731"));
		employeeManager.save(new Employee("Martin Blank", "martinblank@mail.com", "Via Monte Bianco 34, Turin, Italy", "(480) 631-2097"));
	}

	@Override
	public void run(String... args) throws Exception{
		try{
			init();
		}catch (Exception e){
			System.out.println("Data already exists");
		}
	}
}
