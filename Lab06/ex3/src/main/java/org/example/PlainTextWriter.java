package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PlainTextWriter implements TextWriter{

    @Override
    public void write(String filename, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(text);

        writer.close();
    }
}
