package com.leehyukje.webproject.service;

import com.leehyukje.webproject.domain.MemberDTO;
import com.leehyukje.webproject.domain.MemberRole;
import com.leehyukje.webproject.domain.MemberVO;
import com.leehyukje.webproject.persistence.MemberDAO;

import java.util.List;

public interface MemberService {

     MemberVO readOne(String uid) throws Exception;
     List<MemberRole> readRoleList (String fno) throws Exception;
}
