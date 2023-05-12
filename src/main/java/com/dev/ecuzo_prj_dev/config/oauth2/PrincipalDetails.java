package com.dev.ecuzo_prj_dev.config.oauth2;

import com.dev.ecuzo_prj_dev.entity.SnsUsers;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
@Getter
public class PrincipalDetails implements OAuth2User {
    private SnsUsers snsUsers;
    private Map<String, Object> attributes ;

    //OAuth 로그인
    public PrincipalDetails(SnsUsers snsUsers, Map<String, Object> attributes) {
        this.snsUsers=snsUsers;
        this.attributes = attributes;
    }

    @Override
    public Map<String,Object> getAttributes() {
        return attributes;
    }

    //UserDetails의 인터페이스 메소드
    // 해당 USer의 권한을 리턴하는 곳 !
    //securityFilterChain에서 권한을 체크할 때 사용
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                System.out.println("users.getRoleType().name() = " + snsUsers.getRoleType().name());
                return snsUsers.getRoleType().name();
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        return snsUsers.getNickname();
    }

}
