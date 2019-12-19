package com.rebiekong.aliyun.tools.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "com.aliyun.rebie.ddns")
@Data
public class KeyConfig {
    private Map<String, String> keys;
    private String domain;

    public String findDnsByKey(String key) {
        if (keys.containsValue(key)) {
            for (Map.Entry<String, String> o : keys.entrySet()) {
                if (o.getValue().equals(key)) {
                    return o.getKey();
                }
            }
        }
        return null;
    }
}
