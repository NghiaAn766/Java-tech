package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class MyBean {

    private String appName;
    private String appVersion;

    @Override
    public String toString() {
        return "MyBean [appName=" + appName + ", appVersion=" + appVersion + "]";
    }
}
