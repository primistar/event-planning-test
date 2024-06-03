package com.software.eventplanning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@Configuration
@SpringBootApplication
@MapperScan(basePackages = "com.software.eventplanning.mapper")
@EnableWebMvc
public class EventPlanningApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventPlanningApplication.class, args);
    }

}
