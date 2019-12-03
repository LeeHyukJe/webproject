package com.leehyukje.webproject.web;

import com.leehyukje.webproject.domain.CarFilterDTO;
import com.leehyukje.webproject.domain.CarRegisterDTO;
import com.leehyukje.webproject.service.CarRegisterService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Log
@EnableGlobalMethodSecurity(securedEnabled = true)
public class CarCategoryController {

    @Autowired
    CarRegisterService carRegisterService;

    @GetMapping("/car")
    public String car(CarFilterDTO carFilterDTO, Model model, CarRegisterDTO carRegisterDTO) throws Exception{
        if(carFilterDTO.getFilter()) { // 적용 버튼을 누른 상태라면
            log.info(carFilterDTO.toString());
            model.addAttribute("filter", carFilterDTO);


            // TODO 테스트 코드 적용할 것!!!!!
        }
        else
            model.addAttribute("filter",carFilterDTO); // 그렇지 않다면 0

        log.info("새로 추가한 차 정보"+carRegisterService.findAllCarInfo().toString());
        model.addAttribute("carInfo",carRegisterService.findAllCarInfo());
        return "categorized_index";
    }

    // 차량 등록하기
    @PostMapping("/registration")
    public String registration(@RequestParam("file")MultipartFile multipartFile, CarRegisterDTO carRegisterDTO)throws Exception{

        log.info("파일"+multipartFile.getOriginalFilename());
        log.info("들어온 등록 정보"+carRegisterDTO.toString());
        carRegisterService.create(carRegisterDTO);

        return "redirect:/car";
    }

    // 차량 상세 보기
    @Secured("ROLE_NORMAL")
    @GetMapping("/carDetail")
    public String carDetatil(@ModelAttribute CarRegisterDTO carRegisterDTO, Model model) throws Exception{
        log.info("selected carModel Info--------------"+carRegisterDTO.toString());
        model.addAttribute("detail",carRegisterDTO);
        return "detail";
    }



}
