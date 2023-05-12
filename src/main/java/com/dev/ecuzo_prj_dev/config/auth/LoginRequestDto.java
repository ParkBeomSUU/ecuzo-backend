package com.dev.ecuzo_prj_dev.config.auth;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class LoginRequestDto {
    private String userId;
    private String userPw;

}
