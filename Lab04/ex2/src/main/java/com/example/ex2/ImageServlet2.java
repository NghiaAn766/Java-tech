package com.example.ex2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet("/image2")
public class ImageServlet2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("image/jpeg");

        response.setHeader("Content-disposition", "attachment");

        try(InputStream in = request.getServletContext().getResourceAsStream("/WEB-INF/classes/cat.jpg")){
            OutputStream out = response.getOutputStream();

            byte[] buffer = new byte[1048];

            int bytesRead = 0;
            while((bytesRead = in.read(buffer)) > 0){
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
