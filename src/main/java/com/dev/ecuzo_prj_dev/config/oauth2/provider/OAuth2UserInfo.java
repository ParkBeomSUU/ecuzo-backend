package com.dev.ecuzo_prj_dev.config.oauth2.provider;

public interface OAuth2UserInfo {

    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
    String getImageURL();

}
