package com.leehyukje.webproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Locale;

@Setter
@Getter
@ToString
public class MemberDTO {

    private String uid;
    private String upw;
    private String uname;
    private Locale regdate;
    private Locale updatedate;
    private List<MemberRoleVO> fno;

    public MemberDTO(MemberVO memberVO){
        this.uid = memberVO.getUid();
        this.upw = memberVO.getUpw();
        this.uname = memberVO.getUname();
        this.regdate = memberVO.getRegdate();
        this.updatedate = memberVO.getUpdatedate();
    }
}
