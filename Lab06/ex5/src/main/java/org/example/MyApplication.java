package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        MyBean myBean = context.getBean(MyBean.class);
        System.out.println(myBean);

        context.close();
    }
}
