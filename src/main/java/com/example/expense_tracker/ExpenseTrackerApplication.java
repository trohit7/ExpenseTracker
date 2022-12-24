package com.example.expense_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@EnableWebMvc
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ExpenseTrackerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ExpenseTrackerApplication.class, args);
    }


    @Bean
    public WebMvcConfigurer configurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry reg){
                reg.addMapping("/**").allowedOrigins("*");
            }
         };
    }

}
