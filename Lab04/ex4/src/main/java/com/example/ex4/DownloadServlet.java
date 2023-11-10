package com.example.ex4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("filename");
        if (fileName != null) {
            String filePath = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator + fileName;

            File file = new File(filePath);
            if (file.exists()) {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                FileInputStream fileInputStream = new FileInputStream(file);
                IOUtils.copy(fileInputStream, response.getOutputStream());
                fileInputStream.close();
            } else {
                response.getWriter().println("File not found");
            }
        } else {
            response.getWriter().println("Invalid request");
        }
    }
}

