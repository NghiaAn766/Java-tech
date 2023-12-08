DROP DATABASE IF EXISTS `product_manager`;
CREATE DATABASE `product_manager`;
USE `product_manager`;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
    `id` BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255),
    `password` VARCHAR(255),
    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),
    `role` VARCHAR(255) DEFAULT "ROLE_USER"
);

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
    `id` BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255),
    `price` DOUBLE NOT NULL,
    `brand` VARCHAR(255),
    `color` VARCHAR(255)
);

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
    `id` BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `total_price` DOUBLE,
    `account_id` BIGINT(20) NOT NULL,
    FOREIGN KEY (`account_id`) REFERENCES `account`(id)
);

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
    `id` BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `quantity` INT(11) NOT NULL,
    `total_price` DOUBLE NOT NULL,
    `product_id` BIGINT(20) NOT NULL,
    `order_id` BIGINT(20) NOT NULL,
    FOREIGN KEY (`product_id`) REFERENCES `product`(id),
    FOREIGN KEY (`order_id`) REFERENCES `order`(id)
);

INSERT INTO `account` (`email`, `password`, `first_name`, `last_name`, `role`)
VALUES
    ('admin@gmail.com', '$2a$10$b4fPVlmra8fqY6VqMnsL6OWsIZtt/i5XGYIMwkS2Fn0gB4gYSqr8G', 'David', 'Luis' ,'ROLE_ADMIN'),
    ('customer@gmail.com', '$2a$10$b4fPVlmra8fqY6VqMnsL6OWsIZtt/i5XGYIMwkS2Fn0gB4gYSqr8G', 'Jame', 'Thomas', 'ROLE_USER'),
    ('john.doe@example.com', '$2a$10$b4fPVlmra8fqY6VqMnsL6OWsIZtt/i5XGYIMwkS2Fn0gB4gYSqr8G', 'John', 'Doe', 'ROLE_USER'),
    ('jane.smith@example.com', '$2a$10$b4fPVlmra8fqY6VqMnsL6OWsIZtt/i5XGYIMwkS2Fn0gB4gYSqr8G', 'Jane', 'Smith', 'ROLE_USER'),
    ('bob.jones@example.com', '$2a$10$b4fPVlmra8fqY6VqMnsL6OWsIZtt/i5XGYIMwkS2Fn0gB4gYSqr8G', 'Bob', 'Jones', 'ROLE_USER');

INSERT INTO `order` (`total_price`, `account_id`)
VALUES
    (0, 1),
    (0, 2),
    (0, 3);

INSERT INTO `product` (`name`, `price`, `brand`, `color`)
VALUES
    ('Laptop', 1200.00, 'Dell', 'Silver'),
    ('Smartphone', 500.00, 'Samsung', 'Black'),
    ('Headphones', 80.00, 'Sony', 'Red');

INSERT INTO `order_detail` (`product_id`, `order_id`, `quantity`, `total_price`)
VALUES
    (1, 1, 3, 3600),
    (2, 2, 4, 2000),
    (3, 3, 5, 400);