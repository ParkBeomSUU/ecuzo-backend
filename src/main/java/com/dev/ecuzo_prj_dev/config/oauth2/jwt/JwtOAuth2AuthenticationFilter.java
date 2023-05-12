package com.dev.ecuzo_prj_dev.config.oauth2.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtOAuth2AuthenticationFilter extends GenericFilterBean {
    private final JwtTokenOauth2Provider jwtTokenOauth2Provider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("JWT FILTER 실행");
        String token = resolveToken((HttpServletRequest) request);
        log.info("토큰값 :"+token);
        //유효한 토큰인지 확인
        if(token!= null&& jwtTokenOauth2Provider.validateToken(token)){
            log.info("validate와 토큰값 null 아님");
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            Authentication auth = jwtTokenOauth2Provider.getAuthentication(token);
            log.info(auth.getName()+" "+auth.getAuthorities().toString());
            // SecurityContext 에 Authentication 객체를 저장 합니다.
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        else{
            log.error("필터 test 통과 못함");
        }
        chain.doFilter(request, response);
        logger.info("필터 실행 종료");
    }

    private String resolveToken(HttpServletRequest request) {
        logger.info("resoleveTOken 실행");
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            log.info("Baerer: "+bearerToken.substring(7));
            return bearerToken.substring(7);
        }
        return null;
    }
}
