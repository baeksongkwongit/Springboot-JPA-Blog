package com.cos.blog.controller;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OauthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.UUID;

@Controller
public class UserController {

    @Value("$Pcos.key")
    private String cosKey;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/auth/joinForm")
    public String joinfrom(){
        return "user/joinForm";
    }
    @GetMapping("/auth/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String udpatefrom(){
        return "user/updateForm";
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code){

        //Retrofit2
        //Okhttp
        //RestTemplete
        //POST방식으로 key=value방식으로 데이터를 요청(카카오쪽으로)
        RestTemplate rt = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("application", "x-www-form-urlencoded;charset=utf-8");
        MultiValueMap<String, String>  params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","8b05792a24c287d91fb63a6ead4b48a3");
        params.add("redirect_uri","http://localhost:8081/auth/kakao/callback");
        params.add("code",code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =new HttpEntity<>(params, httpHeaders);
        ResponseEntity response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OauthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue((String) response.getBody(),OauthToken.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("카카오 엑세스 토큰 :" + oauthToken.getRefresh_token());


        RestTemplate rt2 = new RestTemplate();

        //HttpHeader 오브젝트생성
        HttpHeaders httpHeaders2 = new HttpHeaders();
        httpHeaders2.add("Authorization","Bearer " + oauthToken.getAccess_token());
        httpHeaders2.add("application", "x-www-form-urlencoded;charset=utf-8");

        //httpHeader와 httpbody 하나의 오브젝트에 담기

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =new HttpEntity<>(httpHeaders2);
        ResponseEntity response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        //Gson, Json Simple, ObjectMapper
        System.out.println(response2.getBody());

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue((String) response2.getBody(),KakaoProfile.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        System.out.println("블로그 서버 유저네임 : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        System.out.println("블로그 서버 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        UUID uuid = UUID.randomUUID();

        System.out.println("블로그 패스워드 : "+ cosKey);

        User kakaoUser = User.builder()
                        .username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
                        .password(cosKey)
                        .email(kakaoProfile.getKakao_account().getEmail())
                        .oauth("kakao")
                        .build();

        User originUser = userService.findId(kakaoUser.getUsername());

        if(originUser.getUsername()==null){
            userService.save(kakaoUser);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return "redirect:/";
    }


}
