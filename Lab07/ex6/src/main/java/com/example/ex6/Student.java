package com.example.ex6;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "student")
public class Student {
    @Id
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @Column(name = "ielts_score")
    private double ieltsScore;

    public Student(long id, String name, int age, String email, double ieltsScore) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.ieltsScore = ieltsScore;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + age + " - " + email + " - " + ieltsScore;
    }
}
