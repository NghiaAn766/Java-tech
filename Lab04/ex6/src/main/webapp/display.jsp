<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lab 4 - Exercise 6</title>
    <!-- Include Bootstrap 5 CSS from a CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">

    <!-- Include Bootstrap 5 JavaScript and Popper.js from a CDN (optional) -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<table class="table caption-top">
    <caption>Registration Information</caption>
    <thead>
    <tr>
        <th scope="col">Field</th>
        <th scope="col">Value</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">Họ tên</th>
        <td><%= request.getAttribute("name") %></td>
    </tr>
    <tr>
        <th scope="row">Email</th>
        <td><%= request.getAttribute("email") %></td>
    </tr>
    <tr>
        <th scope="row">Ngày sinh</th>
        <td><%= request.getAttribute("birthday") %></td>
    </tr>
    <tr>
        <th scope="row">Giờ sinh</th>
        <td><%= request.getAttribute("birthtime") %></td>
    </tr>
    <tr>
        <th scope="row">Giới tính</th>
        <td><%= request.getAttribute("gender") %></td>
    </tr>
    <tr>
        <th scope="row">Quốc gia</th>
        <td><%= request.getAttribute("country") %></td>
    </tr>
    <tr>
        <th scope="row">IDE yêu thích</th>
        <td>
            <% String[] nameList = (String[]) request.getAttribute("favoriteIdeas"); %>
            <% for (String name : nameList) { %>
            <%= name + ", " %>
            <% } %>
        </td>
    </tr>
    <tr>
        <th scope="row">Điểm TOEIC</th>
        <td><%= request.getAttribute("toeic") %></td>
    </tr>
    <tr>
        <th scope="row">Giới thiệu bản thân</th>
        <td><%= request.getAttribute("message") %></td>
    </tr>
    </tbody>
</table>
</body>
</html>
