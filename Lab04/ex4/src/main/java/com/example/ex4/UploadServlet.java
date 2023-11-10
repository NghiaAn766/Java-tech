package com.example.ex4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50) // 50 MB
public class UploadServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Get form parameters
        String fileName = request.getParameter("name");
        Part filePart = request.getPart("document");
        boolean override = request.getParameter("override") != null;

        // Ensure the uploads directory exists
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Get the file extension
        String fileExtension = getExtension(filePart);

        // Check if the file extension is supported
        if (!isSupportedExtension(fileExtension)) {
            response.getWriter().println("Unsupported file extension");
            return;
        }

        // Determine the file path
        String filePath = uploadPath + File.separator + fileName + "." + fileExtension;

        // Check if the file already exists
        File existingFile = new File(filePath);
        if (existingFile.exists() && !override) {
            response.getWriter().println("File already exists");
            return;
        }

        // Save the file
        filePart.write(filePath);
        response.getWriter().println("File has been uploaded");

        // Generate a download link
        String downloadLink = "download?filename=" + fileName + "." + fileExtension;
        response.getWriter().println("<br>File uploaded. Click <a href='" + downloadLink + "'>here</a> to visit the file.");
    }

    // Check if the file extension is supported
    private boolean isSupportedExtension(String extension) {
        String[] supportedExtensions = {"txt", "doc", "docx", "img", "pdf", "rar", "zip"};
        for (String ext : supportedExtensions) {
            if (ext.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    // Get the file extension from the Part
    private String getExtension(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                String fileName = token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('.') + 1);
            }
        }
        return null;
    }
}
