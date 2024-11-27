package com.example.springBoot_selfStudy.controller;

import com.example.springBoot_selfStudy.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //컨트롤러임을 선언한다
public class ArticleController {

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create") //데이터를 보낼 때 post 방식을 채택해서
    public String createArticle(ArticleForm form){ //폼 데이터를 dto로 받기
        System.out.println(form.toString()); //dto에 폼 데이터가 담겼는지 출력값을 통해 확인
        return "";
    }
}
