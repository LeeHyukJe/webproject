package com.leehyukje.webproject.security;

import com.leehyukje.webproject.domain.MemberDTO;
import com.leehyukje.webproject.domain.MemberVO;
import com.leehyukje.webproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserService implements UserDetailsService {

    private final MemberService memberService;

    @Autowired
    public CustomUserService (MemberService memberService){
        this.memberService=memberService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            MemberVO memberVO = memberService.readOne(username);
            MemberDTO memberDTO = new MemberDTO(memberVO);
            memberDTO.setFno(memberService.readRoleList(memberVO.getFno()));
            return new CustomUserDetails(memberDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
