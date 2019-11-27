package com.leehyukje.webproject.service;

import com.leehyukje.webproject.domain.CarRegisterDTO;
import com.leehyukje.webproject.persistence.CarRegisterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarRegisterServiceImpl implements CarRegisterService {

    @Autowired
    private CarRegisterDAO carRegisterDAO;

    @Override
    public void create(CarRegisterDTO carRegisterDTO) throws Exception {
        carRegisterDAO.create(carRegisterDTO);
    }

    @Override
    public List<CarRegisterDTO> findAllCarInfo() throws Exception {
        return carRegisterDAO.findAllCarInfo();
    }
}
