package com.leehyukje.webproject.persistence;

import com.leehyukje.webproject.domain.MemberRoleVO;
import com.leehyukje.webproject.domain.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {

    private final String NAMESPACE = "com.leehyukje.webproject.persistence.MemberDAO";

    @Autowired
    public SqlSession sqlSession;

    @Override
    public MemberVO readOne(String uid) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".selectOne",uid);
    }

    @Override
    public List<MemberRoleVO> readRoleList(String fno) throws Exception {
        return sqlSession.selectList(NAMESPACE+".selectMemberRoles",fno);
    }

    @Override
    public void create(MemberVO memberVO) throws Exception {
        sqlSession.insert(NAMESPACE+".userInsert",memberVO);
    }

}
