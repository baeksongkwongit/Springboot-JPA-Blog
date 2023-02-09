package com.cos.blog.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Reply {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
        private int id; //시퀀스,auto_increment

        @Column(nullable = false, length = 200)
        private String content;

        @ManyToOne
        @JoinColumn(name="boardId")
        private Board board;

        @ManyToOne
        @JoinColumn(name="userId")
        private User user;

        @CreationTimestamp
        private Timestamp createDate;
}
