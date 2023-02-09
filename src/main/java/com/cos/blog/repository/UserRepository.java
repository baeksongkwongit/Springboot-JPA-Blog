package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    //JPA Naming 전략
    //Select * from user where username =? AND password =?;
    User findByUsernameAndPassword(String username, String password);

//    @Query(value="SELECT * FROM user WHERE username =?1 AND password ?2", nativeQuery=true);
//    User login(String username, String password);
}
