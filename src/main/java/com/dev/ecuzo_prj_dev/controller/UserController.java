package com.dev.ecuzo_prj_dev.controller;

import com.dev.ecuzo_prj_dev.config.auth.LoginRequestDto;
import com.dev.ecuzo_prj_dev.config.auth.jwt.TokenInfo;
import com.dev.ecuzo_prj_dev.dto.UserDto;
import com.dev.ecuzo_prj_dev.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;


@RequestMapping(value = "/", produces = "application/json")
@RestController
@Slf4j
public class UserController {

    final private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto)throws IOException {
        System.out.println("로그인 실행");
        String userId=loginRequestDto.getUserId();
        System.out.println("userId = " + userId);
        String userPw=loginRequestDto.getUserPw();
        TokenInfo tokenInfo =userService.login(userId,userPw);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",tokenInfo.getGrantType()+" "+tokenInfo.getAccessToken());
        log.info("Authorization : "+tokenInfo.getGrantType()+" "+tokenInfo.getAccessToken());

        return ResponseEntity.ok().headers(headers).body(HttpStatus.OK);
    }

    @GetMapping("test")
    public String test() {
        return "success";
    }

    @GetMapping("admin")
    public String test1(){
        return "ADMIN PAGE";
    }


    @PostMapping("join")
    public String UserAdd(@RequestBody UserDto userDto){
        userService.create(userDto);
        return "CREATE USERS COMPLETE";
    }

    @GetMapping("user")
    public ResponseEntity<List<UserDto>> userList(){
        return ResponseEntity.ok(userService.userList());
    }


    @GetMapping("userOne")
    public ResponseEntity<UserDto> userSelect(@AuthenticationPrincipal UserDetails userDetails){
        String userId = userDetails.getUsername();
        log.info("userId: "+ userId);
        return ResponseEntity.ok(userService.userSelect(userId));
    }

    @PutMapping("user/{id}")
    public String userUpdate(@PathVariable int id, @RequestBody UserDto userDto){
        userService.userUpdate(id,userDto);
        return "UPDATE USER COMPLETE!!";
    }

    @DeleteMapping("deluser/{id}")
    public String userDelete(@PathVariable int id){
        userService.userDelete(id);
        return "DELETE USERS COMPLETE";
    }
    // 아이디 닉네임 중복체크
    @PostMapping("checkId")
    public Boolean userIdDuplicate(@RequestBody UserDto userDto){
        System.out.println("checkId 실행"+userDto.getUserId());
        return userService.checkUserIdDuplicated(userDto);
    }
//    @PostMapping("checkNick")
//    public Boolean userNickDuplicate(@RequestBody UserDto userDto){
//        return userService.checkUserNickDuplicated(userDto);
//    }


}