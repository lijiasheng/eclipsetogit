package com.simplebank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootsampleApplication {

    public static void main(String[] args) {
        ApplicationContext configurableApplicationContext =
        SpringApplication.run(SpringbootsampleApplication.class, args);
//        configurableApplicationContext.getEnvironment().getProperty("spring")
    }

}
