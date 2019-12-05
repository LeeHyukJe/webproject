package com.leehyukje.webproject.security;

import com.leehyukje.webproject.service.MemberService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Log
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    DataSource dataSource;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        log.info("security config.......");

        http.authorizeRequests().antMatchers("/index/**").permitAll();
        http.authorizeRequests().antMatchers("/item_create").hasRole("NORMAL");
        http.formLogin().loginPage("/login")
                .successHandler(authSuccessHandler)
                .failureHandler(loginFailureHandler);
        //http.formLogin();
        http.exceptionHandling().accessDeniedPage("/accessDenied");
        http.logout().logoutSuccessUrl("/index").invalidateHttpSession(true);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try{
            log.info("build Auth global......");
            auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
        }catch (NullPointerException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
