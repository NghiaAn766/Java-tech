package org.example;

import java.io.IOException;
import java.io.Writer;

public class Application {
    public static void main(String[] args) throws IOException {
        AppConfig appConfig = new AppConfig();

        TextEditor textEditor = appConfig.TextEditor();
        textEditor.input("Hello Spring Boot!!!");
        textEditor.setWriter(appConfig.plainTextWriter());
        textEditor.save("output.txt");
    }
}
