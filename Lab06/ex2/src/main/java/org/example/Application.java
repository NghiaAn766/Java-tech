package org.example;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        Product product1 = appConfig.product1();
        Product product2 = appConfig.product2(product1);
        Product product3 = appConfig.product3();

        System.out.println(product1.getName());
        System.out.println(product2.getName());
        System.out.println(product3.getName());
    }
}
