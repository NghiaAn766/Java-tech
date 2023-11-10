package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

@NoArgsConstructor
@Getter @Setter
public class TextEditor {
    private String text;

    @Autowired
    @Qualifier("plainTextWriter")
    private TextWriter writer;

    public void save(String filename) throws IOException {
        writer.write(filename, text);
    }

    public void input(String text){
        this.text = text;
    }
}
