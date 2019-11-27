package com.leehyukje.webproject.persistence;

import com.leehyukje.webproject.domain.CarRegisterDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRegisterDAOImpl implements CarRegisterDAO{

    protected final static String NAMESPACE="com.leehyukje.webproject.persistence.CarRegisterDAO";

    @Autowired
    public SqlSession sqlSession;


    @Override
    public void create(CarRegisterDTO carRegisterDTO) throws Exception {
        sqlSession.insert(NAMESPACE+".carRegister",carRegisterDTO);

    }

    @Override
    public List<CarRegisterDTO> findAllCarInfo() throws Exception {
        return sqlSession.selectList(NAMESPACE+".findAll");
    }

    @Override
    public CarRegisterDTO findOne(String productsTitle) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".findOne",productsTitle);
    }


}
