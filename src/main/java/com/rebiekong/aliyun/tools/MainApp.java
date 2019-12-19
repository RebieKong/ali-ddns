package com.rebiekong.aliyun.tools;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author RebieKong
 */
@SpringBootApplication
public class MainApp {

    public static void main(String... args) {
        new SpringApplicationBuilder(MainApp.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
