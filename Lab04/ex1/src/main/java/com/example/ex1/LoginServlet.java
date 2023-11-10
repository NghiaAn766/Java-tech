package com.example.ex1;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HashMap<String, String> accounts;

    @Override
    public void init() {
        accounts = new HashMap<>();
        accounts.put("admin", "123456");
        accounts.put("user", "123456");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Tên đăng nhập/Mật khẩu đúng</h2>");
            out.println("</body></html>");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Tên đăng nhập/Mật khẩu không đúng</h2>");
            out.println("</body></html>");
        }
    }
}
