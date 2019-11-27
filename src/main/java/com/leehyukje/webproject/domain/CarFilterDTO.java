package com.leehyukje.webproject.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarFilterDTO {

    private String caryear;
    private String carmileage;
    private String formRadiosSmoking;
    private Boolean filter;

    public CarFilterDTO(){
        this.filter=false;
    }

}
