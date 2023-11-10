package com.example.ex6;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/*")
public class ErrorHandlingFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            response.getWriter().println("An error occurred: " + e.getMessage());
        }
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
