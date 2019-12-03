package com.leehyukje.webproject.persistence;

import com.leehyukje.webproject.domain.MemberRole;
import com.leehyukje.webproject.domain.MemberVO;

import java.util.List;

public interface MemberDAO {

    MemberVO readOne(String uid) throws Exception;
    List<MemberRole> readRoleList(String fno) throws Exception;
}
