package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity //DB가 해당 객체를 인식가능
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 ID를 자동 생성한다. 즉 더미데이터에 맞춰서 알아서 생성
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null) {this.title = article.title;}
        if(article.content != null) {this.content = article.content;}
    }
}
