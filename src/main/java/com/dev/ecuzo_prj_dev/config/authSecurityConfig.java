package com.dev.ecuzo_prj_dev.config;

import com.dev.ecuzo_prj_dev.config.auth.jwt.JwtAuthenticationFilter;
import com.dev.ecuzo_prj_dev.config.auth.jwt.JwtTokenProvider;
import com.dev.ecuzo_prj_dev.config.common.JwtAccessDeniedHandler;
import com.dev.ecuzo_prj_dev.config.common.JwtAuthenticationEntryPoint;
import com.dev.ecuzo_prj_dev.config.oauth2.OAuth2FailureHandler;
import com.dev.ecuzo_prj_dev.config.oauth2.OAuth2SuccessHandler;
import com.dev.ecuzo_prj_dev.config.oauth2.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class authSecurityConfig {
    //WebSecurityConfigurerAdapter 대체로 @Bean 등록후 filterChain을 쓰면 됨.
    private PrincipalOauth2UserService principalOauth2UserService;
    private OAuth2SuccessHandler oAuth2SuccessHandler;
    private OAuth2FailureHandler oAuth2FailureHandler;
    private JwtTokenProvider jwtTokenProvider;
    private CorsFilterConfig corsFilterConfig;
    @Autowired
    public authSecurityConfig(CorsFilterConfig corsFilterConfig,PrincipalOauth2UserService principalOauth2UserService, OAuth2FailureHandler oAuth2FailureHandler,OAuth2SuccessHandler oAuth2SuccessHandler, JwtTokenProvider jwtTokenProvider) {
        this.corsFilterConfig =corsFilterConfig;
        this.principalOauth2UserService = principalOauth2UserService;
        this.oAuth2FailureHandler =oAuth2FailureHandler;
        this.oAuth2SuccessHandler = oAuth2SuccessHandler;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                //세션 사용않하기 때문에 무상태(STATELESS)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeHttpRequests()
                    .antMatchers("/login","/join","/checkId","/checkNick","/snsuserInfo","/snsdel/**","deluser/**").permitAll() //로그인,회원가입
                    .antMatchers("/user/**","/create","/update/**","/userOne","/order").hasAuthority("USER") // USER PAGE 추가
                    .antMatchers("/admin/**","/order/**","delete/**").hasAuthority("ADMIN") //ADMIN PAGE 추가
                .anyRequest().authenticated()
                .and()
                    .oauth2Login()
                    .successHandler(oAuth2SuccessHandler) //JWT 토큰 생성
                    .failureHandler(oAuth2FailureHandler)
                    .userInfoEndpoint()
                    .userService(principalOauth2UserService);
              http
                      .addFilter(corsFilterConfig.corsFilter())
                      .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
