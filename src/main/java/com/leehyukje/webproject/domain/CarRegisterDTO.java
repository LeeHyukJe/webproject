package com.leehyukje.webproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarRegisterDTO {
    private String productsTitle;
    private int price;
    private String description;
    private String modelYear;
    private String carMileage;
    private String smokingOptions;

}
