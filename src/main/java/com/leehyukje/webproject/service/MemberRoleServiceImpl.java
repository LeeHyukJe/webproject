package com.leehyukje.webproject.service;

import com.leehyukje.webproject.domain.MemberRoleVO;
import com.leehyukje.webproject.persistence.MemberRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRoleServiceImpl implements MemberRoleService {

    private final MemberRoleDAO memberRoleDAO;

    @Autowired
    public MemberRoleServiceImpl(MemberRoleDAO memberRoleDAO){
        this.memberRoleDAO = memberRoleDAO;
    }
    @Override
    public void createRole(MemberRoleVO memberRoleVO) throws Exception {
        memberRoleDAO.createRole(memberRoleVO);
    }
}
