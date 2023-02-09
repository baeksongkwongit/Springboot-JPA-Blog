package com.cos.blog.service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public int save(User user){
        try{
            userRepository.save(user);
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("UserService :  회원가입(): " + e.getMessage());
        }
        return -1;
    }

    @Transactional(readOnly = true)
    public User login(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
