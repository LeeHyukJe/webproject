package com.leehyukje.webproject.persistence;

import com.leehyukje.webproject.domain.CarRegisterDTO;

import java.util.List;

public interface CarRegisterDAO {

    public void create(CarRegisterDTO carRegisterDTO) throws Exception;
    public List<CarRegisterDTO> findAllCarInfo() throws Exception;
    public CarRegisterDTO findOne(String productsTitle) throws Exception;
}
