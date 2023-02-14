package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@Entity //user 클래스가 MySQL에 테이블이 생성된다.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; //시퀀스,auto_increment

    @Column(nullable = false, length=100, unique = true)
    private String username;

    @Column(nullable = false, length=100)//123456 => 해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable=false, length=50)
    private String email; //myEmail, my_email

   // @ColumnDefault("'user'")
    @Enumerated(EnumType.STRING)
    private RoleType role; //Enum을 쓰는게 좋다 //admin, user, manager

    @CreationTimestamp //시간 자동 입력
    private Timestamp createDate;

    private String oauth; ///kakao, google
}
