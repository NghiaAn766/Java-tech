package com.example.lab09.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ErrorResponse {
    private HttpStatus status;
    private String message;
}
