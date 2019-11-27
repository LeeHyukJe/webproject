package com.leehyukje.webproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    public void index() throws Exception{

    }

    // 상품 등록 컨트롤러
    @GetMapping("/item_create")
    public String item_create() throws Exception{

        return "item_create";
    }
}
