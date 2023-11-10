package com.example.ex2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileName = request.getParameter("file");
        String speedParam = request.getParameter("speed");

        if (fileName == null || fileName.isEmpty()) {
            throw new ServletException("File name can not be null or empty");
        }

        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (InputStream in = request.getServletContext().getResourceAsStream("/WEB-INF/files/" + fileName);
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            int speed = (speedParam != null) ? Integer.parseInt(speedParam) : Integer.MAX_VALUE;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                int sleepTime = (int) Math.ceil((double) bytesRead * 1000 / speed);

                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }
}
