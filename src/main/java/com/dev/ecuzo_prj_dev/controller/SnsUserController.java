package com.dev.ecuzo_prj_dev.controller;


import com.dev.ecuzo_prj_dev.config.oauth2.PrincipalDetails;
import com.dev.ecuzo_prj_dev.config.oauth2.PrincipalOauth2UserService;
import com.dev.ecuzo_prj_dev.config.oauth2.provider.KakaoUserInfoImpl;
import com.dev.ecuzo_prj_dev.config.oauth2.provider.OAuth2UserInfo;
import com.dev.ecuzo_prj_dev.dto.SnsUserDto;
import com.dev.ecuzo_prj_dev.entity.SnsUsers;
import com.dev.ecuzo_prj_dev.service.SnsUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/")
@RestController
@Slf4j
public class SnsUserController {
    private SnsUserService snsUserService;
    private PrincipalOauth2UserService service;

    @Autowired
    public SnsUserController(SnsUserService snsUserService,PrincipalOauth2UserService service) {
        this.snsUserService = snsUserService;
        this.service=service;

    }
    @GetMapping("snsuserInfo")
    public ResponseEntity<?> kakaoProfile(){
        return ResponseEntity.ok(snsUserService.userSelect());
    }

    @DeleteMapping("snsdel/{email}")
    public String delKakao(@PathVariable String email){
        snsUserService.deleteEmail(email);
        return "Del Complete";
    }



//    @GetMapping("/snsUser")
//    public ResponseEntity<?> kakaoInfo(@AuthenticationPrincipal UserDetails userDetails){
//        String email = userDetails.getUsername();
//        snsUserService.findByEmail()
//        return ResponseEntity.ok()
//    }

}
