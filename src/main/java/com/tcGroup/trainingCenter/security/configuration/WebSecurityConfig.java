package com.tcGroup.trainingCenter.security.configuration;

import com.tcGroup.trainingCenter.security.filter.CORSFilter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcGroup.trainingCenter.user.provider.CustomAuthProvider;
import com.tcGroup.trainingCenter.user.response.AuthResponse;
import com.tcGroup.trainingCenter.user.service.AccountManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
    CORSFilter corsFilter() {
        return new CORSFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(corsFilter(), SessionManagementFilter.class).httpBasic()
                .and().authorizeRequests().antMatchers("/resources/**", "/scss/**", "/webjars/**", "/error", "/", "/index", "/register", "/login", "/exercises/**", "/exercise/**", "/tags").permitAll()
                .and().csrf().ignoringAntMatchers("/phpmyadmin/**", "/register", "/login*", "/exercises/**", "/exercise/**", "/tags").and().headers()
                .frameOptions().sameOrigin()
                .and().formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(successHandler())
                    .failureHandler(failureHandler())
                    .permitAll()
                .and().logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll();
    }

    private AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse, Authentication authentication)
                    throws IOException, ServletException {
                httpServletResponse.getWriter().append(new AuthResponse("OK").toString());
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setStatus(200);
            }
        };
    }
    
    private AuthenticationFailureHandler failureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse, AuthenticationException e)
                    throws IOException, ServletException {
                httpServletResponse.getWriter().append(new AuthResponse(e.getMessage()).toString());
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setStatus(401);
            }
        };
    }
}