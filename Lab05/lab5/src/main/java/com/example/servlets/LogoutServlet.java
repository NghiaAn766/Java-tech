package com.example.servlets;

import com.example.dao.UserDAO;
import com.example.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.io.IOException;
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Clear the login-related cookies
        Cookie emailCookie = new Cookie("email", "");
        emailCookie.setMaxAge(0); // Set cookie to expire immediately
        response.addCookie(emailCookie);

        Cookie passwordCookie = new Cookie("name", "");
        passwordCookie.setMaxAge(0);
        response.addCookie(passwordCookie);

        // Invalidate the session
        request.getSession().invalidate();

        response.sendRedirect("/login");
    }
}