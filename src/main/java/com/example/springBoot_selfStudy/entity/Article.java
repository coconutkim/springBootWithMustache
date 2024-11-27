package com.example.springBoot_selfStudy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
//jpa가 제공하는 어노테이션
// 이 클래스를 기반으로 데이터베이스에 테이블이 생성된다
public class Article {

    @Id //대푯값으로 선언
    @GeneratedValue //대푯값을 자동으로 생성
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
