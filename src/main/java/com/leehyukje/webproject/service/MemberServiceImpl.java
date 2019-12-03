package com.leehyukje.webproject.service;

import com.leehyukje.webproject.domain.MemberRoleVO;
import com.leehyukje.webproject.domain.MemberVO;
import com.leehyukje.webproject.persistence.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{


    private final MemberDAO memberDAO;

    @Autowired
    public MemberServiceImpl (MemberDAO memberDAO){
        this.memberDAO=memberDAO;
    }
    @Override
    public MemberVO readOne(String uid) throws Exception {

        return memberDAO.readOne(uid);
    }

    @Override
    public List<MemberRoleVO> readRoleList(String fno) throws Exception {
        return memberDAO.readRoleList(fno);
    }

    @Override
    public void create(MemberVO memberVO) throws Exception {
        memberDAO.create(memberVO);
    }

}
