package com.example.SpringBaseStarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/1").permitAll()
            .antMatchers("/2","/3").authenticated()
            .and()
            .httpBasic();
        }


        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    
    /*-----------------------*/










































    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     // TODO Auto-generated method stub
    //     http
    //     .csrf().disable()
    //     .authorizeRequests()
    //     .antMatchers("/public/**").permitAll()
    //     .antMatchers("/**").authenticated()
    //     .and()
    //     .httpBasic();
    //     // .anyRequest().authenticated()
    // }
    
    
    // @Bean
    // public PasswordEncoder passwordEncoder(){
    //     return new BCryptPasswordEncoder();
    // }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        auth
        .inMemoryAuthentication()
        .withUser("user")
        .password(passwordEncoder().encode("123"))
        .roles("USER");
    }
}
