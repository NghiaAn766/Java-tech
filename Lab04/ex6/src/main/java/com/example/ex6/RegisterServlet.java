package com.example.ex6;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String birthtime = request.getParameter("birthtime");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String[] favoriteIdeas = request.getParameterValues("favorite_ide[]");
        String toeic = request.getParameter("toeic");
        String message = request.getParameter("message");

        // Check for missing information
        if (name == null || email == null || birthday == null || birthtime == null || gender == null ||
                country == null || favoriteIdeas == null || toeic == null || message == null) {
            out.println("Missing information. Please fill in all fields.");
        } else {
            // Set the data as request attributes
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("birthday", birthday);
            request.setAttribute("birthtime", birthtime);
            request.setAttribute("gender", gender);
            request.setAttribute("country", country);
            request.setAttribute("favoriteIdeas", favoriteIdeas);
            request.setAttribute("toeic", toeic);
            request.setAttribute("message", message);

            // Forward the request to the output.jsp file
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/display.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        doPost(req, resp);
    }
}

