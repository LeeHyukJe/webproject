package com.leehyukje.webproject.persistence;

import com.leehyukje.webproject.domain.MemberRoleVO;
import com.leehyukje.webproject.domain.MemberVO;

import java.util.List;

public interface MemberDAO {

    MemberVO readOne(String uid) throws Exception;
    List<MemberRoleVO> readRoleList(String fno) throws Exception;
    void create(MemberVO memberVO) throws Exception;

}
