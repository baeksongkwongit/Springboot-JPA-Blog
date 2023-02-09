package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; //시퀀스,auto_increment


    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    @ColumnDefault("0")
    private int count;

    @ManyToOne(fetch = FetchType.EAGER) //Many = Many , User = One
    @JoinColumn(name="userId")
    private User user; //DB오브젝트를 저장할 수 없다.  FK, 자바는 오프젝트를 저장할수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다.(난 FK가 아니다.)
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createData;

}
