package com.dev.ecuzo_prj_dev.service;

import com.dev.ecuzo_prj_dev.config.auth.jwt.TokenInfo;
import com.dev.ecuzo_prj_dev.config.oauth2.jwt.JwtTokenOauth2Provider;
import com.dev.ecuzo_prj_dev.dto.SnsUserDto;
import com.dev.ecuzo_prj_dev.entity.SnsUsers;
import com.dev.ecuzo_prj_dev.jpa.SnsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Slf4j
public class SnsUserServiceImpl implements SnsUserService{

    private final SnsRepository snsRepository;
    private JwtTokenOauth2Provider jwtTokenOauth2Provider;
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    public SnsUserServiceImpl(SnsRepository snsRepository, AuthenticationManagerBuilder authenticationManagerBuilder,JwtTokenOauth2Provider jwtTokenOauth2Provider) {
        this.snsRepository = snsRepository;
        this.authenticationManagerBuilder =authenticationManagerBuilder;
        this.jwtTokenOauth2Provider=jwtTokenOauth2Provider;
    }

    @Override
    public Optional<SnsUsers> findByEmail(String email) {
        Optional<SnsUsers> snsUsers = snsRepository.findByEmail(email);
        return snsUsers;
    }

    @Override
    public SnsUserDto userSelect() {
        List<SnsUsers> snsUsers =snsRepository.findAll();
        SnsUserDto snsUserDto =snsUsers.get(0).toDto();
        return snsUserDto;
    }

    @Override
    public void deleteEmail(String email) {
        Optional<SnsUsers> snsUsers = snsRepository.findByEmail(email);
        snsRepository.delete(snsUsers.get());
    }

    //    public String searchKakaoUser(String token) throws Exception {
//        String reqURL = "https://kapi.kakao.com/v2/user/me";
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            URL url = new URL(reqURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//            conn.setRequestProperty("Authorization", "Bearer " + token);
//
//            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode = " + responseCode);
//
//            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
//            String streamToString = IOUtils.toString(inputStream,"UTF-8");
//            System.out.println("streamToString = " + streamToString);
//            ObjectMapper mapper =new ObjectMapper();
//            Map<String,Map<String,String>> map = mapper.readValue(streamToString,Map.class);
//            String email =map.get("kakao_account").get("email");
//            return email;
//        }catch(IOException e){
//            e.printStackTrace();
//            return "";
//        }
//    }
//    public String getKakaoAccessToken (String code)throws Exception {
//        String access_Token = "";
//        String refresh_Token = "";
//        String reqURL = "https://kauth.kakao.com/oauth/token";
//        try {
//            URL url = new URL(reqURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//            StringBuilder sb = new StringBuilder();
//            sb.append("grant_type=authorization_code");
//            sb.append("&client_id=1cfc5abbc1e0dd24af7409e5284da67a"); // TODO REST_API_KEY 입력
//            sb.append("&redirect_uri=http://localhost:8080/kakaoinfo"); // TODO 인가코드 받은 redirect_uri 입력
//            sb.append("&code=" + code);
//            bw.write(sb.toString());
//            bw.flush();
//
//            //결과 코드가 200이라면 성공
//            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode : " + responseCode);
//            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line = "";
//            String result = "";
//            while ((line = br.readLine()) != null) {
//                result += line;
//            }
//            System.out.println("response body : " + result);
//
//            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(result);
//            access_Token = element.getAsJsonObject().get("access_token").getAsString();
//            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
//            System.out.println("access_token : " + access_Token);
//            System.out.println("refresh_token : " + refresh_Token);
//            br.close();
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return access_Token;
//    }
}
