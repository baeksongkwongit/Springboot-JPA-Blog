package com.cos.blog.test;

import com.cos.blog.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest : ";

    @GetMapping("/http/lombok")
    public String lombokTest(){
//        Member m = new Member(1, "saar", "1234", "email");
        Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
        System.out.println(TAG + "getter : " + m.getId());
        m.setId(5000);
        System.out.println(TAG + "getter : " + m.getId());
        return("Lombok 종료");
    }

    @GetMapping("/http/get")
    public String getTest(Member m){

        return "Get요청 : " + m.getId()+ "m" + m.getUsername() + "," + m.getPassword() + ","+m.getEmail();
    }
    @GetMapping("/http/post")
    public String postTest(Member m){
        System.out.println();
        System.out.println();
        return "Post요청 : " + m.getId()+ "m" + m.getUsername() + "," + m.getPassword() + ","+m.getEmail();
    }

    @GetMapping("/http/put")
    public String putTest(Member m){
        System.out.println();
        System.out.println();
        return "Put요청 : " + m.getId()+ "m" + m.getUsername() + "," + m.getPassword() + ","+m.getEmail();
    }

    @GetMapping("/http/delete")
    public String deleteTest(Member m){
        System.out.println();
        System.out.println();
        return "Delete요청 : " + m.getId()+ "m" + m.getUsername() + "," + m.getPassword() + ","+m.getEmail();
    }
}