package com.example.ex2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;

    public Employee(String name, String email, String address, String phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
}
