package com.dev.ecuzo_prj_dev.config.auth.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        log.info("JWT FILTER 실행");
        String token = resolveToken((HttpServletRequest) request);
//        logger.info("토큰값 :"+token);
        //유효한 토큰인지 확인
        if(token!= null&& jwtTokenProvider.validateToken(token)){
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            // SecurityContext 에 Authentication 객체를 저장 합니다.
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
//        logger.info("필터 실행 종료");
    }

    private String resolveToken(HttpServletRequest request) {
//        logger.info("resolveToken 실행");
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            log.info("Bearer: "+bearerToken.substring(7));
            return bearerToken.substring(7);
        }
        return null;
    }
}
