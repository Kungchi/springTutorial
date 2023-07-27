package com.example.firstproject.service;

import com.example.firstproject.dTO.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {

        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.creatCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 에외 처리
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 댓글 엔티티 DB 저장
        Comment created = commentRepository.save(comment);

        // DTO로 반환
        return CommentDto.creatCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long Id, CommentDto dto) {
//        //게시글 조회
//        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
//
//        //기존 댓글 조회
//        Comment taget = commentRepository.findById(dto.getId()).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 댓글이 없습니다."));
//
//        //댓글 수정
//        Comment comment = Comment.createComment(dto, article);
//
//        taget.patch(comment);
//
//        // DTO로 반환
//        return CommentDto.creatCommentDto(taget);


        Comment taget = commentRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 댓글이 없습니다."));
        taget.patch(dto);
        Comment updated = commentRepository.save(taget);
        return CommentDto.creatCommentDto(updated);
    }
}
