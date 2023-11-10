<%@ page import="javax.swing.*" %>
<%@ page import="com.example.model.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Danh sách sản phẩm</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
        function confirmDelete(productId) {
            var confirmed = confirm("Are you sure you want to delete this product?");
            if (confirmed) {
                window.location.href = "ProcessProduct?action=delete&id=" + productId;
            }
        }
    </script>
</head>
<body style="background-color: #f8f8f8">

<%-- Check for the "name" cookie --%>
<%
    String userName = null;
    if (session.getAttribute("name") != null) {
        userName = session.getAttribute("name").toString();
    }

    if (userName == null) {
        response.sendRedirect("/login");
    }
%>

<div class="container-fluid p-5">
    <div class="row mb-5">
        <div class="col-md-6">
            <h3>Product Management</h3>
        </div>
        <div class="col-md-6 text-right">
            Xin chào <span class="text-danger"><%= userName %></span> | <a href="logout">Logout</a>
        </div>
    </div>
    <div class="row rounded border p-3">
        <div class="col-md-4">
            <h4 class="text-success">Thêm sản phẩm mới</h4>
            <form class="mt-3" method="post" action="ProcessProduct">
                <div class="form-group">
                    <label for="product-name">Tên sản phẩm</label>
                    <input class="form-control"  type="text" placeholder="Nhập tên sản phẩm" id="product-name" name="productName">
                </div>
                <div class="form-group">
                    <label for="price">Giá sản phẩm</label>
                    <input class="form-control" type="number" placeholder="Nhập giá bán" id="price" name="productPrice">
                </div>
                <div class="form-group">
                    <button class="btn btn-success mr-2" type="submit">Thêm sản phẩm</button>
                </div>

                <div class="alert alert-danger">
                    Vui lòng nhập tên sản phẩm
                </div>
            </form>
        </div>
        <div class="col-md-8">
            <h4 class="text-success">Danh sách sản phẩm</h4>
            <p>Chọn một sản phẩm cụ thể để xem chi tiết</p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Product> productList = (List<Product>) request.getAttribute("productList");
                    if (productList != null) {
                        for (Product product : productList) {
                %>
                <tr>
                    <td><%= product.getId() %></td>
                    <td><a href="#"><%= product.getName() %></a></td>
                    <td><%= product.getPrice() %></td>
                    <td>
                        <a href="javascript:void(0);" onclick="confirmDelete('<%= product.getId() %>');" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>
</body>
</html>
