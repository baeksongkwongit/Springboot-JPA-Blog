package com.cos.blog.config;

import com.cos.blog.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//빈등록 : 스프링 컨테이너에서 객체를 관리할수 있게 하는것
@Configuration //빈등록(IOC관리)
@EnableWebSecurity //필터 추가 = 스프링 시큐리티가 활성화가 되어있는데 어떤 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크 하겠다는 뜻
public class Securityconfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PrincipalDetailService principalDetailService;
    @Bean //IOC가되요
    public BCryptPasswordEncoder encodePWD(){
//        String encPassword = new BCryptPasswordEncoder().encode("1234");
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
    //해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    //같은 해쉬로 암호화 해서 DB에 있는 해쉬랑 비교할 수 있음.

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() //csrf토큰 비활성화(테스트시 걸어두는 게 좋음)
            .authorizeRequests()
                .antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
            .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/"); //스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
    }

}
