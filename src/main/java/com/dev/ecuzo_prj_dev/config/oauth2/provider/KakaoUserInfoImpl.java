package com.dev.ecuzo_prj_dev.config.oauth2.provider;

import java.util.Map;


public class KakaoUserInfoImpl implements OAuth2UserInfo{
    private Map<String, Object> attributes;
    private Map<String, Object> properties;
    private Map<String, Object> kakaoAccount;

    public KakaoUserInfoImpl(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        kakaoAccount = (Map)attributes.get("kakao_account");
        return kakaoAccount.get("email").toString();
    }

    @Override
    public String getName() {
        properties = (Map)attributes.get("properties");
        return properties.get("nickname").toString();
    }

    @Override
    public String getImageURL() {
        properties = (Map)attributes.get("properties");
        return properties.get("thumbnail_image").toString();

    }
}
