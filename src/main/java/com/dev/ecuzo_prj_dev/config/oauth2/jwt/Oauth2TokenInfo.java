package com.dev.ecuzo_prj_dev.config.oauth2.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Oauth2TokenInfo {
    private String grantType;
    private String accessToken;

}
