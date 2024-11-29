package com.example.springBoot_selfStudy.controller;

import com.example.springBoot_selfStudy.dto.ArticleForm;
import com.example.springBoot_selfStudy.entity.Article;
import com.example.springBoot_selfStudy.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j //로깅 기능으로 로그를 찍으면 나중에라도 그동안 찍힌 로그를 찾아볼 수 있다
//simple logging facade for java
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

        log.info(form.toString());
//        System.out.println(form.toString()); //dto에 폼 데이터가 담겼는지 출력값을 통해 확인

        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString()); //dto가 엔티티로 잘 변환되는지 확인

        Article saved = articleRepository.save(article);
        //crudrepository를 상속받았기에 가능한 기능
        log.info(saved.toString());
        //System.out.println(saved.toString()); //article이 데이터베이스에 저장되는지 확인
        return "";
    }
    
    @GetMapping("/articles/{id}") //데이터 조회 요청을 접수한다
    //컨트롤러에서 url 변수를 사용할 때는 중괄호 하나만 사용한다
    public String show(@PathVariable Long id, Model model){ //매개변수로 id를 받는다
        //위의 어노테이션은 url 요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져온다
        log.info("id = " + id);

        //아이디를 조회해 데이터를 가져온다
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //해당 아이디 값이 없으면 null을 반환하라
        //모델에 데이터를 등록한다
        model.addAttribute("article", articleEntity);
        return "articles/show";
    }
}
