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

@WebServlet("/ProcessLogin")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean rememberMe = "true".equals(request.getParameter("rememberMe"));

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            if (rememberMe) {
                // Remember username and password using cookies
                Cookie emailCookie = new Cookie("email", email);
                emailCookie.setMaxAge(30 * 24 * 60 * 60);
                response.addCookie(emailCookie);

                String name = userDAO.getUserByEmail(email).getName();
                Cookie nameCookie = new Cookie("name", name);
                nameCookie.setMaxAge(30 * 24 * 60 * 60);
                response.addCookie(nameCookie);

            }
            String name = userDAO.getUserByEmail(email).getName();
            request.getSession().setAttribute("name", name);
            request.setAttribute("name", name);

            response.sendRedirect("/ProcessProduct");
        } else {
            // Set flash message for error
            request.getSession().setAttribute("flashMessage", "Invalid email or password");
            response.sendRedirect("/login");

        }
    }
}