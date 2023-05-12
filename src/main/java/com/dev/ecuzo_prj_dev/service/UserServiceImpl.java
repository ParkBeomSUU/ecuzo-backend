package com.dev.ecuzo_prj_dev.service;

import com.dev.ecuzo_prj_dev.config.auth.jwt.JwtTokenProvider;
import com.dev.ecuzo_prj_dev.config.auth.jwt.TokenInfo;
import com.dev.ecuzo_prj_dev.dto.UserDto;
import com.dev.ecuzo_prj_dev.entity.Role;
import com.dev.ecuzo_prj_dev.entity.Users;
import com.dev.ecuzo_prj_dev.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtTokenProvider jwtTokenProvider
    ,AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManagerBuilder=authenticationManagerBuilder;
    }

    //    private final KakaoOAuth kakaoOAuth;
//
    @Transactional
    public TokenInfo login(String userId, String userPw){
        System.out.println("login service 실행");
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId,userPw);
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId,userPw);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenInfo tokenInfo =jwtTokenProvider.createToken(authentication);
        return tokenInfo;
    }

    @Override
    public void create(UserDto userDto){
        //비밀번호 암호화
        String encoderPassword= bCryptPasswordEncoder.encode(userDto.getUserPw());
        userDto.setUserPw(encoderPassword);
        //RoleType
        if("admin".equals(userDto.getUserId())){
            userDto.setRoleType(Role.ADMIN);
            userDto.setTableNum("admin");
        }
        else{
            userDto.setRoleType(Role.USER);
        }

        Users users=userDto.toEntity();
        userRepository.save(users);
        System.out.println("DB 저장 완료");
        UserDto entityToDto=users.toDto();
        System.out.println("entityToDto = " + entityToDto);

    }

    @Transactional
    @Override
    public List<UserDto> userList() {
        List<Users> users = userRepository.findAll();
        UserDto userDto =new UserDto();
        List<UserDto> userDtoList = users.stream().map(m->m.toDto()).collect(Collectors.toList());
        System.out.println("userDtoList = " + userDtoList);
        return userDtoList;
    }
    @Transactional
    @Override
    public UserDto userSelect(String userId) {
        Users users = userRepository.findByUserId(userId);
        UserDto userDto = users.toDto();
        System.out.println("userDto = " + userDto);
        return userDto;
    }
    @Transactional
    @Override
    public void userUpdate(int id, UserDto userDto){
        Users users = userRepository.findById(id).get();

        UserDto update = new UserDto();
        System.out.println("before = " + update);
        update.setUserId(userDto.getUserId());
        update.setUserPw(userDto.getUserPw());
        users=update.toEntity();
        System.out.println("after = " + update);
        userRepository.save(users);
    }

    @Override
    public void userDelete(int id){
        Users users = userRepository.findById(id).get();
        userRepository.delete(users);
    }

    @Override
    public Boolean checkUserIdDuplicated(UserDto userDto) {
        boolean checkId = userRepository.existsByUserId(userDto.getUserId());
        System.out.println("checkId = " + checkId);
        return checkId;
    }

//    @Override
//    public Boolean checkUserNickDuplicated(UserDto userDto) {
//        boolean checkNick = userRepository.existsByUserNick(userDto.getUserNick());
//        System.out.println("checkNick = " + checkNick);
//        return checkNick;
//    }


}
