package com.example.springBoot_selfStudy.repository;

import com.example.springBoot_selfStudy.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    //<> 관리 대상 엔티티의 클래스, 관리 대상 엔티티의 대푯값 타입
    //여기서는 id 타입을 따른다

}
