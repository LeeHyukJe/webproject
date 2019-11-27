package com.leehyukje.webproject;

import com.leehyukje.webproject.service.CarRegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebprojectApplicationTests {

    @Autowired
    CarRegisterService carRegisterService;

    @Test
    void contextLoads() throws Exception {
        System.out.println(carRegisterService.findAllCarInfo().toString());
    }

}
