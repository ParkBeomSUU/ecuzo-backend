package com.dev.ecuzo_prj_dev.config.oauth2;

import com.dev.ecuzo_prj_dev.config.oauth2.provider.KakaoUserInfoImpl;
import com.dev.ecuzo_prj_dev.config.oauth2.provider.OAuth2UserInfo;
import com.dev.ecuzo_prj_dev.dto.SnsUserDto;
import com.dev.ecuzo_prj_dev.entity.Role;
import com.dev.ecuzo_prj_dev.entity.SnsUsers;
import com.dev.ecuzo_prj_dev.entity.Users;
import com.dev.ecuzo_prj_dev.jpa.SnsRepository;
import com.dev.ecuzo_prj_dev.service.SnsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final SnsUserService snsUserService;
    private final SnsRepository snsRepository;

    @Autowired
    public PrincipalOauth2UserService(SnsRepository snsRepository, SnsUserService snsUserService) {
        this.snsUserService = snsUserService;
        this.snsRepository =snsRepository;

    }

    //카카오 로부터 받은 userRequest에 대한 후처리 -> 데이터에 넣고 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어짐
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("principalOauth2실행");
        /*
        동작설명: 리엑트에서 http://localhost:8080/oauth2/authorization/kakao으로 로그인 요청 하면 dispather 서블릿에 가기전에는
        filter을 거치게 되는데 Configure에서 필터에 관한 내용을 확인함. Login처리를 Oauth2Login에 인가해 application.yml 명령을 실행 시킨후
        Oauth2UserREquest에 담기게 됨 그걸 loaduser를 이용해 Oauth2User에 담음
         */
        System.out.println("Oauth2User 실행시작");
        System.out.println("userRequest.getClientRegistration() = " + userRequest.getClientRegistration());
        System.out.println("userRequest.getAccessToken().getTokenValue() = " + userRequest.getAccessToken().getTokenValue());
        
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = null;
        //소셜 로그인 분기점
        if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")){
            System.out.println("카카오 로그인 요청입니다.");
            oAuth2UserInfo = new KakaoUserInfoImpl(oAuth2User.getAttributes());
        }
        
        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
        String nickName = oAuth2UserInfo.getName();
        System.out.println("nickName = " + nickName);
        String imageName = oAuth2UserInfo.getName()+"_img";
        System.out.println("imageName = " + imageName);
        String imageURL= oAuth2UserInfo.getImageURL();
        System.out.println("imageURL = " + imageURL);
        Role roleType =Role.USER;

        Optional<SnsUsers> snsUsers =snsUserService.findByEmail(email);

        SnsUsers snsUsersGo =null;

        if(snsUsers.isPresent()){
            snsUsersGo=snsUsers.get();
            System.out.println("로그인 정보가 있습니다. 자동으로 로그인 합니다.");
        }
        else{
            System.out.println("카카오 로그인 최초입니다.");
            snsUsersGo = snsRepository.save(SnsUsers
                    .builder()
                    .nickname(nickName)
                    .email(email)
                    .imageName(imageName)
                    .imagePath(imageURL)
                    .roleType(Role.USER)
                    .build());
        }
        System.out.println(snsUsersGo.getId());
        return new PrincipalDetails(snsUsersGo, oAuth2User.getAttributes());
    }


}