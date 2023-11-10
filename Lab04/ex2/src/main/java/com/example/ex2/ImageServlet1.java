package com.example.ex2;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet("/image1")
public class ImageServlet1 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");

        ServletOutputStream out = response.getOutputStream();
        FileInputStream fin = new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/dog.jpg"));

        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(out);

        int bytesRead = 0;
        while ((bytesRead = bin.read()) != -1) {
            bout.write(bytesRead);
        }

        bin.close();
        fin.close();
        bout.close();
        out.close();
    }
}
