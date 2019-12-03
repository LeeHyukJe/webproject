package com.leehyukje.webproject.persistence;

import com.leehyukje.webproject.domain.MemberRoleVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRoleDAOImpl implements MemberRoleDAO {

    private final String NAMESPACE = "com.leehyukje.webproject.persistence.MemberDAO";

    private final SqlSession sqlSession;
    @Autowired
    public MemberRoleDAOImpl(SqlSession sqlSession){
        this.sqlSession=sqlSession;
    }

    @Override
    public void createRole(MemberRoleVO memberRoleVO) throws Exception {
        sqlSession.insert(NAMESPACE+".roleInsert",memberRoleVO);
    }
}
