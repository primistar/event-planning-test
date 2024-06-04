package com.software.eventplanning.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${knife4j.basic.username}")
    private String username;
    @Value("${knife4j.basic.password}")
    private String password;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        //放掉某些特定不需要校验token的路由
        registry.addInterceptor(jwtInterceptor())
                .excludePathPatterns("/login", "/getEmailCode/**", "/register", "/sendEmail")
                .excludePathPatterns("/favicon.ico", "/*.jpg", "/*.png", "/*.js")
                .excludePathPatterns("/doc.html", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        ;

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
