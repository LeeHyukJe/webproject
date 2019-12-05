package com.leehyukje.webproject.crawal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.UUID;


@Log
@Getter
@Setter
@ToString
public class ContentVO {

    private String uuid;
    private String title;
    private String cotent;
}
