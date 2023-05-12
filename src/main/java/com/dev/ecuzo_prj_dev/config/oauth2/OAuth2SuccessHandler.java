package com.dev.ecuzo_prj_dev.config.oauth2;

import com.dev.ecuzo_prj_dev.config.oauth2.jwt.JwtTokenOauth2Provider;
import com.dev.ecuzo_prj_dev.config.oauth2.jwt.Oauth2TokenInfo;
import com.dev.ecuzo_prj_dev.config.oauth2.provider.KakaoUserInfoImpl;
import com.dev.ecuzo_prj_dev.config.oauth2.provider.OAuth2UserInfo;
import com.dev.ecuzo_prj_dev.dto.SnsUserDto;
import com.dev.ecuzo_prj_dev.entity.SnsUsers;
import com.dev.ecuzo_prj_dev.jpa.SnsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private JwtTokenOauth2Provider jwtTokenOauth2Provider;
    private SnsRepository snsRepository;

    @Autowired
    public OAuth2SuccessHandler (JwtTokenOauth2Provider jwtTokenOauth2Provider,SnsRepository snsRepository){
        this.jwtTokenOauth2Provider = jwtTokenOauth2Provider;
        this.snsRepository =snsRepository;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("Success 핸들러 실행");
//         로그인 한 유저 정보 받아오기
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String roleType =oAuth2User.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        log.info(roleType);
        OAuth2UserInfo oAuth2UserInfo = null;
        oAuth2UserInfo = new KakaoUserInfoImpl(oAuth2User.getAttributes());
//        log.info("출력시작");
        String providerId = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
//        log.info("email = " + email);
//        log.info("토큰 발행 시작");
        Oauth2TokenInfo oauth2TokenInfo = jwtTokenOauth2Provider.createToken(email,roleType);
        String grantType =oauth2TokenInfo.getGrantType();
        String accessToken =oauth2TokenInfo.getAccessToken();

//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId,userPw);
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        log.info("GrantType: "+oauth2TokenInfo.getGrantType());
//        log.info("AccessToken: "+oauth2TokenInfo.getAccessToken());
//
////         헤더에 token 정보들 담기
//        response.setHeader("Authorization",grantType+" "+accessToken);
//        log.info("HEADER 담긴 정보들: " +response.getHeader("Authorization")+" ||");
        response.sendRedirect("https://web-ecuzo-react-cloud-108dypx2ale6e8i6k.sel3.cloudtype.app/kakao/login");
    }


}
