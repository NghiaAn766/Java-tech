package org.example;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class PlainTextWriter implements TextWriter{

    @Override
    public void write(String filename, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(text);

        writer.close();
    }
}
