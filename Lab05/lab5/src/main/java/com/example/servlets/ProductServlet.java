package com.example.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import com.example.dao.ProductDAO;
import com.example.model.Product;
@WebServlet(urlPatterns = {"/ProcessProduct", "/"})
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO; // Assuming you have a ProductDAO instance

    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("delete")) {
            String productId = request.getParameter("id");
            int id = Integer.parseInt(productId);
            boolean success = productDAO.remove(id);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/ProcessProduct");
                return;
            }
        }

        List<Product> productList = productDAO.getAll();

        request.setAttribute("productList", productList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String productName = request.getParameter("productName");
        String priceString = request.getParameter("productPrice");
        double price = 0.0;


        if (priceString != null && !priceString.trim().isEmpty()) {
            try {
                price = Double.parseDouble(priceString);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        Product product = new Product(productName, price);

        boolean success = productDAO.add(product);
        response.sendRedirect("/ProcessProduct");
    }
}