package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Transactional
    public int fnidId(User user){
        try{
            userRepository.save(user);
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("UserService :  회원가입(): " + e.getMessage());
        }
        return -1;
    }
    @Transactional
    public int save(User user) {
        String rawPassword = user.getPassword(); // 1234 원문
        String encPassword = encoder.encode(rawPassword); // 해쉬
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        try {
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            return -1;
        }

    }
    @Transactional
    public void update(User user){
        //수정시에는 Jpa영속성 User오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
        //select 를 해서 user 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기위해서
        //영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.
        User persistance = userRepository.findById(user.getId())
                .orElseThrow(()->{
            return new IllegalArgumentException("회원찾기실패");
        });

        if(persistance.getOauth()==null || persistance.getOauth().equals("")){
            String rawPassword  = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            persistance.setPassword(encPassword);
            persistance.setEmail(user.getEmail());
        }
        //회웢수정 함수 종료시 = 서비스 종료시 = 트랜잭션종료 = commit
        //영속화디ㅗㄴ persistance 객체의 변화가 감지되면 더티체킹이 되어 변화된것들을 update문을 날려준다.

    }
    @Transactional(readOnly = true)
    public User findId(String username){

        User user = userRepository.findByUsername(username).orElseGet(()->{
            return new User();
        });
        return user;
    }
//    @Transactional(readOnly = true)
//    public User login(User user){
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
