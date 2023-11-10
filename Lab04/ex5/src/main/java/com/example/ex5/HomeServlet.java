package com.example.ex5;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đọc và phân tích chuỗi truy vấn từ URL
        String page = request.getParameter("page");

        // Kiểm tra xem trang nào được yêu cầu và chuyển hướng
        if (page == null || page.isEmpty()) {
            // Nếu không có hoặc truy vấn sai, đưa ra trang chào mừng
            response.getWriter().write("Welcome to our website");
        } else if (page.equals("about")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/about.jsp");
            dispatcher.forward(request, response);
        } else if (page.equals("contact")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/contact.jsp");
            dispatcher.forward(request, response);
        } else if (page.equals("help")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/help.jsp");
            dispatcher.forward(request, response);
        } else {
            // Trong trường hợp truy vấn không hợp lệ, đưa ra trang chào mừng
            response.getWriter().write("Welcome to our website");
        }
    }
}
