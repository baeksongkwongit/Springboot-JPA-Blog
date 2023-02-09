package com.cos.blog.controller.api;

import com.cos.blog.config.Securityconfig;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

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
