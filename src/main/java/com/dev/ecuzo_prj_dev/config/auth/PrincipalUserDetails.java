package com.dev.ecuzo_prj_dev.config.auth;

import com.dev.ecuzo_prj_dev.entity.Users;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalUserDetails implements UserDetails{


    private Users users;

    public PrincipalUserDetails(Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("getAuthorites 실행 + UserDetails");
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                System.out.println("users roleType = " + users.getRoleType());
                return users.getRoleType().toString();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return users.getUserPw();
    }

    @Override
    public String getUsername() {
        return users.getUserId();
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
