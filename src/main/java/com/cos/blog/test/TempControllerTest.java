package com.cos.blog.test;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
    @GetMapping("/temp/home")
    public String tempHome(){
        return "home.html";
    }
    @GetMapping("/temp/img")
    public String TempImage(){
        return "a/png";
    }
    @GetMapping("/temp/jsp")
    public String imageJsp(){
        return "test.jsp";
    }
}
