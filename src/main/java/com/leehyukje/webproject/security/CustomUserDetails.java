package com.leehyukje.webproject.security;

import com.leehyukje.webproject.domain.MemberDTO;
import com.leehyukje.webproject.domain.MemberRoleVO;
import lombok.extern.java.Log;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Log
public class CustomUserDetails implements UserDetails {
    private static final  String ROLE_PREFIX = "ROLE_";
    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(MemberDTO memberDTO){
        this.username=memberDTO.getUid();
        this.password=memberDTO.getUpw();
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for(MemberRoleVO memberRole:memberDTO.getFno()){
            authorityList.add(new SimpleGrantedAuthority(ROLE_PREFIX+memberRole.getRoleName().toUpperCase()));
        }
        this.authorities=authorityList;
        log.info("@@@@AuthorityList"+authorities.toString());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
