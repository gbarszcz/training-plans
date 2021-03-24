package com.tcGroup.trainingCenter.security.configuration;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        // TODO to be configured
        /*http.authorizeRequests().antMatchers("/resources/**", "/scss/**", "/webjars/**", "/", "/index", "/register").permitAll()
                .and().csrf().ignoringAntMatchers("/phpmyadmin/**").and().headers()
                .frameOptions().sameOrigin(); */
    }

}