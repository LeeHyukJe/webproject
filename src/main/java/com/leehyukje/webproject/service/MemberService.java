package com.leehyukje.webproject.service;

import com.leehyukje.webproject.domain.MemberRoleVO;
import com.leehyukje.webproject.domain.MemberVO;

import java.util.List;

public interface MemberService {

     MemberVO readOne(String uid) throws Exception;
     List<MemberRoleVO> readRoleList (String fno) throws Exception;
     void create(MemberVO memberVO) throws Exception;

}
