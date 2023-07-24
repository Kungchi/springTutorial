package com.example.firstproject.controller;

import com.example.firstproject.dTO.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticlesController {
    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결! --> 핸들을 오른쪽으로 꺽으면 오른쪽으로간다 등등 굳이 핸들안에 톱니가 맞물려서 같은거 알필요없어서 그냥 씀
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form)
    {
        System.out.println(form.toString());

        Article article = form.toEntity();
        System.out.println(article.toString());

        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }

}
