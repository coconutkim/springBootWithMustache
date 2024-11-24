package com.example.springBoot_selfStudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//소스 코드에 추가해 사용하는 메타 데이터의 일종
//컴파일 및 실행 과정에서 코드를 어떻게 처리해야 할지 알려주는 추가 정보
@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "seventeen");
       return "greetings"; //greetings.mustache 파일을 반환한다
    }
}
