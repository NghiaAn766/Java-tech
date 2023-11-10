package org.example;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class PdfTextWriter implements TextWriter{
    @Override
    public void write(String filename, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.append("This is Pdf TextWriter\n");
        writer.write(text);

        writer.close();
    }
}
