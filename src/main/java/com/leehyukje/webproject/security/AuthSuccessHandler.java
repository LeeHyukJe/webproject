package com.leehyukje.webproject.security;

import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Log
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();



    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        resultRedirectStrategy(httpServletRequest,httpServletResponse,authentication);
        clearAuthenticationAttributes(httpServletRequest);
    }


    protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, SecurityException{
        SavedRequest savedRequest = requestCache.getRequest(request,response);


        if(savedRequest!=null){ // 직전 url이 존재한다면
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("target@@@@@@@"+targetUrl);
            redirectStrategy.sendRedirect(request,response,targetUrl);
        }
        else{ // 직접 바로 로그인할 경우
            redirectStrategy.sendRedirect(request,response,"/index");
        }

        log.info("로그인 완료!!");
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request){
        HttpSession session  = request.getSession(false);
        if(session==null) return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
