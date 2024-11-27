package com.example.springBoot_selfStudy.controller;

import com.example.springBoot_selfStudy.dto.ArticleForm;
import com.example.springBoot_selfStudy.entity.Article;
import com.example.springBoot_selfStudy.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //컨트롤러임을 선언한다
public class ArticleController {

    @Autowired //스프링 부트가 미리 생성해 놓은 레포지토리 객체를 주입한다
    //일명 dependency injection
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create") //데이터를 보낼 때 post 방식을 채택해서
    public String createArticle(ArticleForm form){
        //폼 데이터를 dto로 받기

        System.out.println(form.toString()); //dto에 폼 데이터가 담겼는지 출력값을 통해 확인

        Article article = form.toEntity();
        System.out.println(article.toString()); //dto가 엔티티로 잘 변환되는지 확인

        Article saved = articleRepository.save(article);
        //crudrepository를 상송받았기에 가능한 기능
        System.out.println(saved.toString()); //article이 데이터베이스에 저장되는지 확인
        return "";
    }
}
