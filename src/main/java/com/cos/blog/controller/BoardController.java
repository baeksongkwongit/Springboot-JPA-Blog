package com.cos.blog.controller;

import com.cos.blog.auth.PrintipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BoardController {
    @GetMapping({"","/"})
    public String index(@AuthenticationPrincipal PrintipalDetail printipal){
        System.out.println("로그인 사용자 : " + printipal.getUsername());
        // /WEB-INF/views/index.jsp
        return "index";
    }
}
