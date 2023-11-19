-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS employee_manager;

-- Sử dụng cơ sở dữ liệu mới tạo
USE employee_manager;

-- Tạo bảng Employee
CREATE TABLE IF NOT EXISTS employee (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20)
);

-- Thêm các ràng buộc cho email để đảm bảo tính duy nhất
ALTER TABLE employee ADD CONSTRAINT unique_email UNIQUE (email);
