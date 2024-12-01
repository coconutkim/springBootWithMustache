package com.example.springBoot_selfStudy.controller;

import com.example.springBoot_selfStudy.dto.ArticleForm;
import com.example.springBoot_selfStudy.entity.Article;
import com.example.springBoot_selfStudy.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Iterator;
import java.util.List;

@Slf4j //로깅 기능으로 로그를 찍으면 나중에라도 그동안 찍힌 로그를 찾아볼 수 있다
//simple logging facade for java
@Controller //컨트롤러임을 선언한다
public class ArticleController {

    @Autowired //스프링 부트가 미리 생성해 놓은 레포지토리 객체를 주입한다
    //일명 dependency injection
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create") //데이터를 보낼 때 post 방식을 채택해서
    public String createArticle(ArticleForm form) {
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
        return "redirect:/articles/" + saved.getId();
        //아이디에 따라 url 주소를 달라지게 한다
    }

    @GetMapping("/articles/{id}") //데이터 조회 요청을 접수한다
    //컨트롤러에서 url 변수를 사용할 때는 중괄호 하나만 사용한다
    public String show(@PathVariable Long id, Model model) { //매개변수로 id를 받는다
        //위의 어노테이션은 url 요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져온다
        log.info("id = " + id);

        //아이디를 조회해 데이터를 가져온다
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //해당 아이디 값이 없으면 null을 반환하라
        //모델에 데이터를 등록한다
        model.addAttribute("article", articleEntity);
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        //모든 데이터를 리스트 형태로 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        //해당 레포지토리에 있는 모든 데이터를 가져온다
        //모델에 데이터를 등록한다
        model.addAttribute("articleList", articleEntityList);
        //뷰 페이지를 설정한다
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        //데이터베이스에서 데이터를 가져올 때 findbyid 메서드를 이용해서 데이터를 찾는다
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        //모델에 데이터를 등록한다
        //뷰 페이지를 설정한다
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        //수정한 데이터는 dto로 받는다
        log.info(form.toString());
        //수정 데이터를 잘 받았는지 확인한다
        Article articleEntity = form.toEntity(); //dto를 엔티티로 변환
        log.info(articleEntity.toString()); //엔티티로의 변환 여부 확인

        //데이터베이스에서 기존 데이터를 가져온다
        Article target = articleRepository
                .findById(articleEntity.getId()).orElse(null);

        //기존 데이터 값을 갱신한다
        if (target != null){
            articleRepository.save(articleEntity); //엔티티를 데이터베이스에 저장
        }

        return "redirect:/articles/"+articleEntity.getId();
    }
}