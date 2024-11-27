package com.example.springBoot_selfStudy.dto;

import com.example.springBoot_selfStudy.entity.Article;

public class ArticleForm {

    // 필드 선언
    private Long id;
    private String title;
    private String content;

    // Getter와 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // toEntity 메서드
    public Article toEntity() {
        return new Article(id, title, content);
    }
}
