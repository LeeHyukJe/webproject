package com.leehyukje.webproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Locale;

@Getter
@Setter
@ToString
public class MemberVO {

    private String uid;
    private String upw;
    private String uname;
    private Locale regdate;
    private Locale updatedate;
    private String fno;

}
