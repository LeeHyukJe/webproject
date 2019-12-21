package com.leehyukje.webproject.web;

import com.leehyukje.webproject.domain.MemberRoleVO;
import com.leehyukje.webproject.domain.MemberVO;
import com.leehyukje.webproject.persistence.MemberDAO;
import com.leehyukje.webproject.service.MemberRoleService;
import com.leehyukje.webproject.service.MemberService;
import com.leehyukje.webproject.validator.LoginValidator;
import com.leehyukje.webproject.validator.ValidationException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
public class LoginController {
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private final MemberRoleService memberRoleService;
    private final LoginValidator loginValidator;

    @Autowired
    public LoginController (PasswordEncoder passwordEncoder,
                            MemberService memberService,
                            MemberRoleService memberRoleService,
                            LoginValidator loginValidator){
        this.passwordEncoder=passwordEncoder;
        this.memberService = memberService;
        this.memberRoleService= memberRoleService;
        this.loginValidator = loginValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(loginValidator);
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
    public String joinPost(@ModelAttribute @Validated MemberVO memberVO, BindingResult result, Model model){
        try{

            if(result.hasErrors()){
                log.info(result.getFieldError().getCode());
                model.addAttribute("error",result.getFieldError().getCode());
                return "signup";
            }
            else {
                log.info("회원가입!!" + memberVO.toString());
                String encrypPw = passwordEncoder.encode(memberVO.getUpw());
                log.info("패스워드 암호화!!@@@@" + encrypPw);
                memberVO.setUpw(encrypPw);
                memberVO.setFno("85");

                // 첫 회원가입의 ROLE은 반드시 NORMAL 일 것!!
                MemberRoleVO memberRoleVO = new MemberRoleVO();
                memberRoleVO.setFno(memberVO.getFno());
                memberRoleVO.setRoleName("NORMAL");

                //memberRoleService.createRole(memberRoleVO);
                memberService.create(memberVO);
            }

        }catch (ValidationException valid){
            log.info("[signup error]"+valid.getErrors()[0].getDefaultMessage());
            valid.printStackTrace();
            model.addAttribute("error",valid.getErrors()[0].getDefaultMessage());
            return "signup";
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return "redirect:/index";
    }
}
