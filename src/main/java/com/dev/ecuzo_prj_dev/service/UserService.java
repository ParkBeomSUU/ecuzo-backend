package com.dev.ecuzo_prj_dev.service;

import com.dev.ecuzo_prj_dev.config.auth.jwt.TokenInfo;
import com.dev.ecuzo_prj_dev.dto.UserDto;

import java.util.List;

public interface UserService {
    public void create(UserDto userDto);
    public List<UserDto> userList();
    public UserDto userSelect(String userId);
    public void userDelete(int id);
    public void userUpdate(int id, UserDto userDto);
    Boolean checkUserIdDuplicated(UserDto userDto);
//    Boolean checkUserNickDuplicated(UserDto userDto);
    public TokenInfo login(String userId, String userPw);

}
