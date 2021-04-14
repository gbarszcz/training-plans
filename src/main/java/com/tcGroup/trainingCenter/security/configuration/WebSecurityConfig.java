package com.tcGroup.trainingCenter.security.configuration;

import com.tcGroup.trainingCenter.security.filter.CORSFilter;
import com.tcGroup.trainingCenter.user.service.AccountManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

     @Autowired
     private AccountManagementService accountService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(accountService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Bean
    CORSFilter corsFilter() {
        return new CORSFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(corsFilter(), SessionManagementFilter.class).authorizeRequests().antMatchers("/resources/**", "/scss/**", "/webjars/**", "/", "/index", "/register", "/exercises/**", "/exercise/**", "/tags").permitAll()
                .anyRequest().authenticated().and().csrf().ignoringAntMatchers("/phpmyadmin/**", "/register", "/exercises/**", "/exercise/**", "/tags").and().headers()
                .frameOptions().sameOrigin();
    }

}