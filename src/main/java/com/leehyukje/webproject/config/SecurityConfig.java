package com.leehyukje.webproject.config;

import com.leehyukje.webproject.domain.MemberDTO;
import com.leehyukje.webproject.domain.MemberVO;
import com.leehyukje.webproject.service.MemberService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    DataSource dataSource;

    @Autowired
    private MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        log.info("security config.......");

        http.authorizeRequests().antMatchers("/index/**").permitAll();
        http.authorizeRequests().antMatchers("/item_create").hasRole("NORMAL");
        http.formLogin();
        http.exceptionHandling().accessDeniedPage("/accessDenied");
        http.logout().logoutUrl("/logout").invalidateHttpSession(true);
        http.userDetailsService(username->{
            try {
                MemberVO memberVO = memberService.readOne(username);
                MemberDTO memberDTO = new MemberDTO(memberVO);
                memberDTO.setFno(memberService.readRoleList(memberVO.getFno()));
                return new CustomUserDetails(memberDTO);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}
