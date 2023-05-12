package com.dev.ecuzo_prj_dev.config.oauth2;

import com.dev.ecuzo_prj_dev.config.oauth2.jwt.JwtTokenOauth2Provider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class OAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message="";
        final ObjectMapper mapper =new ObjectMapper();
        final Map<String,Object> body =new HashMap<>();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if(exception instanceof InsufficientAuthenticationException) {
            log.error("권한이 없습니다. 다시 시도해주세요.");
            message="권한이 없습니다.";
            body.put("status", response.SC_UNAUTHORIZED);
            body.put("message",message);

        }
    }
}
