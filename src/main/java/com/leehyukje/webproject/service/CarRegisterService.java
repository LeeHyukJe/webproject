package com.leehyukje.webproject.service;

import com.leehyukje.webproject.domain.CarRegisterDTO;

import java.util.List;

public interface CarRegisterService {

    public void create(CarRegisterDTO carRegisterDTO) throws Exception;
    public List<CarRegisterDTO> findAllCarInfo() throws Exception;
}
