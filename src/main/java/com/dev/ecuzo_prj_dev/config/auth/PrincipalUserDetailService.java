package com.dev.ecuzo_prj_dev.config.auth;

import com.dev.ecuzo_prj_dev.entity.Users;
import com.dev.ecuzo_prj_dev.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Service
public class PrincipalUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override //유저의 정보를 불러와서 UserDetails로 리턴
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetailsService 시작");
        log.warn("userName: "+username);
        Users users = userRepository.findByUserId(username);
        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(users.getRoleType().toString()));
        PrincipalUserDetails principalUserDetails = new PrincipalUserDetails(users);
        System.out.println("principalUserDetails name= " + principalUserDetails.getUsername());
        System.out.println("principalUserDetails pw= " + principalUserDetails.getPassword());
        System.out.println("principalUserDetails roletype= " + principalUserDetails.getAuthorities().toString());
        System.out.println("UserDetailsService 종료");
        return principalUserDetails;
    }
}


