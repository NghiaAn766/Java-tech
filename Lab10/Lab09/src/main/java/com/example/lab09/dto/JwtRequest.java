package com.example.lab09.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class JwtRequest implements Serializable {
    private String email;
    private String password;
}
