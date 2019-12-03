package com.leehyukje.webproject.persistence;

import com.leehyukje.webproject.domain.MemberRoleVO;

public interface MemberRoleDAO {
    void createRole(MemberRoleVO memberRoleVO) throws Exception;
}
