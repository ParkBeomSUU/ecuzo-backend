package com.dev.ecuzo_prj_dev.config.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //유효한 자격증명을 제공하지 않고 접근하려고 할때 401(인증 실패)
        log.error("가입되지 않은 사용자 접근");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"잘못된 로그인 요청입니다. 다시 시도 해주세요.");
        response.sendRedirect("http://localhost:3000/"); //나중 로그인페이지 정해졌으면 그때 사용
    }
}
