package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        TextEditor textEditor = context.getBean(TextEditor.class);
        textEditor.input("Hello Spring Boot!!!");
        textEditor.setWriter(context.getBean(PdfTextWriter.class));
        textEditor.save("output.txt");
    }
}

