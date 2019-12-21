package com.leehyukje.webproject.validator;

import com.leehyukje.webproject.domain.MemberVO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LoginValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return MemberVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"uid","이메일은 필수 항목입니다.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"upw","비밀번호는 필수 항목입니다.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"uname","사용자 이름은 필수 항목입니다.");
        MemberVO memberVO = (MemberVO) target;
        // 아이디가 이메일 형식인지 정규식으로 일치판단하기
        String targetRex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+";
        Pattern regex = Pattern.compile(targetRex);
        Matcher matcher = regex.matcher(memberVO.getUid());
        if(!matcher.find()){
            errors.rejectValue("uid","이메일 형식과 맞지 않습니다.");
        }

        // 회원가입 아이디 중복 검사

    }

}
