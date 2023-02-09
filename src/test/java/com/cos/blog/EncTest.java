package com.cos.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class EncTest {
    @Test
    public void encTest(){
        String encPassword = new BCryptPasswordEncoder().encode("1234");
        System.out.println("1234해쉬 " + encPassword);
    }
}
