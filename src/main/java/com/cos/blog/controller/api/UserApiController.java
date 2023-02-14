package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController:Save 호출됨.");

        user.setRole(RoleType.USER);

        String password = user.getPassword();
        String encPassword = encoder.encode(password);
        user.setPassword(encPassword);

        int result = userService.save(user);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){
        userService.update(user);

        //여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경되지만
        //하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.

//        Authentication authentication =
////                new UsernamePasswordAuthenticationToken(printipal, null, printipal.getAuthorities());
////        SecurityContext securityContext = SecurityContextHolder.getContext();
////        securityContext.setAuthentication(authentication);
////        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
////
        //세션등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }
//    @PostMapping("/auth/loginProc")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession httpSession) {
//        System.out.println("UserApiController:login 호출됨.");
//
//        User principal = userService.login(user);
//
//        if (principal != null){
//            httpSession.setAttribute("principal", principal);
//        }
//
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }
}
