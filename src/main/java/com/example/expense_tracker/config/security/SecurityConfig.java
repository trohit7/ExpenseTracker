package com.example.expense_tracker.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}password")
                .roles("USER");
    }


    public void configure(WebSecurity registry) throws Exception {
        registry.ignoring()
                .antMatchers("/docs/**").antMatchers("/actuator/**").antMatchers("/v2/api-docs", "/configuration/ui",
                        "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**");
    }




//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
//    {
//        http
//                .csrf().disable()
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .httpBasic();
//
//        return http.build();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception
//    {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{noop}password")
//                .roles("USER");
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(WebSecurity registry) throws Exception {
//        return (web) -> registry.ignoring()
//                .antMatchers("/docs/**").antMatchers("/actuator/**").antMatchers("/v2/api-docs", "/configuration/ui",
//                        "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**");
//
//    }

}
