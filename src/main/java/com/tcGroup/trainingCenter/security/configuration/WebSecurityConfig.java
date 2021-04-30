package com.tcGroup.trainingCenter.security.configuration;

import com.tcGroup.trainingCenter.security.filter.CORSFilter;
import com.tcGroup.trainingCenter.security.filter.CsrfHeaderFilter;
import com.tcGroup.trainingCenter.security.handler.CORSLogoutSuccessHandler;
import com.tcGroup.trainingCenter.user.provider.CustomAuthProvider;
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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

     @Autowired
     private AccountManagementService accountService;

     @Autowired
     private CustomAuthProvider authProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(accountService).passwordEncoder(bCryptPasswordEncoder())
            .and().authenticationProvider(authProvider);
    }

    @Bean(value = "bCryptPasswordEncoder")
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Bean
    protected CORSFilter corsFilter() {
        return new CORSFilter();
    }

    @Bean
    protected CORSLogoutSuccessHandler corsLogoutSuccessHandler() {
        return new CORSLogoutSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(corsFilter(), SessionManagementFilter.class).httpBasic().and().authorizeRequests()
            .antMatchers("/user", "/logout", "/profile").authenticated()
            .antMatchers("/resources/**", "/scss/**", "/webjars/**", "/error", "/", "/index", "/register", "/login", "/exercises/**", "/exercise/**", "/tags", "/account/**", "/training/**").permitAll()
                .and().csrf().ignoringAntMatchers("/phpmyadmin/**", "/register", "/login*", "/logout*", "/exercises/**", "/exercise/**", "/tags", "/account/**", "/training/**", "/profile")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().headers()
                .frameOptions().sameOrigin()
                .and().logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(corsLogoutSuccessHandler())
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                .and()
                .addFilterAfter(new CsrfHeaderFilter(), SessionManagementFilter.class);
    }
}