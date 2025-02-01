package com.easyWay.Student_Management_System.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:*", "https://student-management-system-gui-3.onrender.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/{spring:\\w+}").setViewName("forward:/index.html");
        registry.addViewController("/**/{spring:\\w+}").setViewName("forward:/index.html");
        registry.addViewController("/{spring:\\w+}/**{spring:?!(\\.js|\\.css|\\.ico|\\.json)$}")
                .setViewName("forward:/index.html");
    }
}
