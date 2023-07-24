package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "김상훈");
        return "greetings"; // 템플릿안에 greetings.mustache를 리턴한다 즉 브라우저에 저파일을 보여준다는 말임.
    }

    @GetMapping("/bye")
     public String seeYouNext(Model model) {
        model.addAttribute("nickname", "홍길동");
        return "goodBye";
    }
}
