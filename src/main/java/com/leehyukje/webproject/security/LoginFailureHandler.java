package com.leehyukje.webproject.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");


        String errorMsg = null;


        if(e instanceof BadCredentialsException){
            errorMsg = "아이디 또는 비밀번호가 일치 하지 않습니다.";
        }
        else if(e.getCause()==null){
            errorMsg = "등록된 회원 정보가 없습니다. 회원가입을 하시기 바랍니다 .";
        }

        httpServletRequest.setAttribute("username",username);
        httpServletRequest.setAttribute("password",password);
        httpServletRequest.setAttribute("errorMsg",errorMsg);
        httpServletRequest.setAttribute("isLogin","false");
        httpServletRequest.getRequestDispatcher("/login?error").forward(httpServletRequest,httpServletResponse);
    }
}
