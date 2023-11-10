package com.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    private List<Product> products = new ArrayList<>();

    public void init(){
        products.add(new Product(1, "Iphone", 17990000));
        products.add(new Product(2, "Samsung", 16590000));
        products.add(new Product(3, "Xiaomi", 7990000));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ApiResponse apiResponse;

        if (id == null) {
            apiResponse = new ApiResponse(0, "Đọc sản phẩm thành công", products);
        } else {
            int productId = Integer.parseInt(id);
            Product product = findProductById(productId);

            if (product != null) {
                apiResponse = new ApiResponse(0, "Đọc sản phẩm thành công", product);
            } else {
                apiResponse = new ApiResponse(1, "Không tìm thấy sản phầm nào với mã số " + id, null);
            }
        }

        String jsonResponse = new Gson().toJson(apiResponse);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));

            if (isProductExists(id)) {
                ApiResponse apiResponse = new ApiResponse(1, "Sản phẩm với id " + id + " đã tồn tại", null);
                response.setContentType("application/json");
                response.getWriter().write(new Gson().toJson(apiResponse));
            } else {
                Product newProduct = new Product(id, name, price);
                products.add(newProduct);

                ApiResponse apiResponse = new ApiResponse(0, "Đã thêm sản phẩm thành công", newProduct);
                response.setContentType("application/json");
                response.getWriter().write(new Gson().toJson(apiResponse));
            }
        } catch (Exception e) {
            ApiResponse apiResponse = new ApiResponse(2, "Invalid input format, id must be an integer and price must be a valid number.", null);
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(apiResponse));
        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));

            Product productToUpdate = findProductById(id);

            if (productToUpdate == null) {
                ApiResponse apiResponse = new ApiResponse(1, "Sản phẩm với id " + id + " không tồn tại, cập nhật thất bại!", null);
                response.setContentType("application/json");
                response.getWriter().write(new Gson().toJson(apiResponse));
            } else {
                productToUpdate.setName(name);
                productToUpdate.setPrice(price);

                ApiResponse apiResponse = new ApiResponse(0, "Đã cập nhật sản phẩm thành công", productToUpdate);
                response.setContentType("application/json");
                response.getWriter().write(new Gson().toJson(apiResponse));
            }
        } catch (Exception e) {
            ApiResponse apiResponse = new ApiResponse(2, "Invalid input format, id must be an integer and price must be a valid number.", null);
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(apiResponse));
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Product productToDelete = findProductById(id);

            if (productToDelete != null) {
                products.remove(productToDelete);
                ApiResponse apiResponse = new ApiResponse(0, "Đã xóa sản phẩm thành công", null);
                response.setContentType("application/json");
                response.getWriter().write(new Gson().toJson(apiResponse));
            } else {
                ApiResponse apiResponse = new ApiResponse(1, "Sản phẩm với id " + id + " không tồn tại, xóa thất bại!", null);
                response.setContentType("application/json");
                response.getWriter().write(new Gson().toJson(apiResponse));
            }
        } catch (Exception e) {
            ApiResponse apiResponse = new ApiResponse(2, "Invalid input format, id must be an integer.", null);
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(apiResponse));
        }
    }

    private Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    private boolean isProductExists(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return true;
            }
        }
        return false;
    }
}


