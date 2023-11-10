package com.example.servlets;

import com.example.dao.UserDAO;
import com.example.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ProcessRegister")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("errorMessage");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            // Set flash message for validation errors
            request.setAttribute("errorMessage", "Invalid form data. Please check your inputs.");
            request.getRequestDispatcher("/register").forward(request, response);
            return;
        }
        if (userDAO.getUserByEmail(email) != null) {
            request.setAttribute("errorMessage", "Email already exists. Please choose a different email.");
            request.getRequestDispatcher("/register").forward(request, response);
            return;
        }

        if (!password.equals(passwordConfirm)) {
            request.setAttribute("errorMessage", "Confirm password do not match.");

            request.getRequestDispatcher("/register").forward(request, response);
            return;
        }

        User user = new User(name, email, password);
        boolean success = userDAO.add(user);

        if (success) {
            response.sendRedirect("/ProcessProduct");
        } else {
            response.sendRedirect("/register");
        }
    }
}