package com.example.firstproject.controller;

import com.example.firstproject.dTO.ArticleForm;
import com.example.firstproject.dTO.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j //로깅
public class ArticlesController {
    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결! --> 핸들을 오른쪽으로 꺽으면 오른쪽으로간다 등등 굳이 핸들안에 톱니가 맞물려서 같은거 알필요없어서 그냥 씀
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form)
    {
        log.info(form.toString());

        Article article = form.toEntity();
        log.info(article.toString());

        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);

        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {

        ArrayList<Article> articleEntityList = articleRepository.findAll();

        model.addAttribute("articleList", articleEntityList);


        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {

       Article articleEntity = articleRepository.findById(id).orElse(null);

       model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {

        Article articleEntity = form.toEntity();

       Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

       if(target != null)
       {
           articleRepository.save(articleEntity);
       }
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
       Article target = articleRepository.findById(id).orElse(null);
       if (target != null) {
           articleRepository.delete(target);
           rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
       }

        return "redirect:/articles";
    }
}
