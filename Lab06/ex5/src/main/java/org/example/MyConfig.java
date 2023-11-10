package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@PropertySources({
        @PropertySource("classpath:application.properties")
})
public class MyConfig {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Bean
    public MyBean myBean() {
        MyBean myBean = new MyBean();
        myBean.setAppName(appName);
        myBean.setAppVersion(appVersion);
        return myBean;
    }
}
