package com.example.springBoot_selfStudy.dto;

import com.example.springBoot_selfStudy.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    // 필드 선언
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
