package com.leehyukje.webproject.web;

import com.leehyukje.webproject.domain.MemberRoleVO;
import com.leehyukje.webproject.domain.MemberVO;
import com.leehyukje.webproject.persistence.MemberDAO;
import com.leehyukje.webproject.service.MemberRoleService;
import com.leehyukje.webproject.service.MemberService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
public class LoginController {
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private final MemberRoleService memberRoleService;

    @Autowired
    public LoginController (PasswordEncoder passwordEncoder,
                            MemberService memberService,
                            MemberRoleService memberRoleService){
        this.passwordEncoder=passwordEncoder;
        this.memberService = memberService;
        this.memberRoleService= memberRoleService;
    }



    @RequestMapping("/login")
    public void login() throws Exception{

    }



    @GetMapping("/accessDenied")
    public void accessDenied(){

    }

    @RequestMapping("/logout")
    public void logout(){

    }

    @GetMapping("/signup")
    public String join(){
        return "signup";
    }

    @PostMapping("/join")
    public String joinPost(@ModelAttribute MemberVO memberVO){
        try{
            log.info("회원가입!!"+memberVO.toString());
            String encrypPw = passwordEncoder.encode(memberVO.getUpw());
            log.info("패스워드 암호화!!@@@@"+encrypPw);
            memberVO.setUpw(encrypPw);
            memberVO.setFno("85");

            // 첫 로그인의 ROLE은 반드시 NORMLA일 것!!
            MemberRoleVO memberRoleVO = new MemberRoleVO();
            memberRoleVO.setFno(memberVO.getFno());
            memberRoleVO.setRoleName("NORMAL");

            //memberRoleService.createRole(memberRoleVO);
            memberService.create(memberVO);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return "redirect:/index";
    }
}
