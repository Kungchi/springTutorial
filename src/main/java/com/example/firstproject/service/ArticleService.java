package com.example.firstproject.service;

import com.example.firstproject.dTO.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Service // 서비스 선언
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
       return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
       Article article = dto.toEntity();
       if(article.getId() != null) { return null; }
       return articleRepository.save(article);

    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        Article target = articleRepository.findById(id).orElse(null);
        target.patch(article);
        return (target == null || id != article.getId()) ? null : articleRepository.save(target);
    }

    public void delete(Long id) {
      articleRepository.deleteById(id);
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
       List<Article> articleList = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
       articleList.stream().forEach(article -> articleRepository.save(article));


       return articleList;
    }
}
