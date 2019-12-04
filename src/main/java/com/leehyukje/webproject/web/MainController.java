package com.leehyukje.webproject.web;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
public class MainController {

    @GetMapping("/index")
    public void index(HttpServletRequest request, Model model) throws Exception{
        log.info("@@사용자 정보1"+request.isUserInRole("ROLE_NORMAL"));
        if(request.getRemoteUser()!=null) {
            log.info("@@사용자 정보2" + request.getRemoteUser());
            model.addAttribute("isLogin",request.getRemoteUser());
        }
    }

    // 상품 등록 컨트롤러
    @GetMapping("/item_create")
    public String item_create() throws Exception{

        return "item_create";
    }
}
