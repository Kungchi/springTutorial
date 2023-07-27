package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //jpa 연동 테스트
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void finndByArticleId() {

        /* 4번 게시글 댓글 조회 */
        {
            Long articleId = 4L;

            // 실제
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            Article article = new Article(4L, "당신의 인생영화는?", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "Park", "굳 윌 헌팅");
            Comment b = new Comment(2L, article, "Kim", "아이 엠 샘");
            Comment c = new Comment(3L, article, "Choi", "쇼생크의 탈출");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 비교
            assertEquals(expected.toString(), comments.toString());
        }

        /* 1번 게시글 댓글 조회 */
        {
            Long articleId = 1L;

            // 실제
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            List<Comment> expected = Arrays.asList();

            //비교
            assertEquals(expected, comments);

        }

    }

    @DisplayName("특정 닉네임 댓글 조회")
    @Test
    void findByNickname() {

        /* park이라는 이름의 댓글을 모두 조회 */
        {
            String nickname = "Park";
            //예상
            List<Comment> comments = commentRepository.findByNickname(nickname);

            //실제
            Article article4 = new Article(4L, "당신의 인생영화는?", "댓글 ㄱ");
            Article article5 = new Article(5L, "당신의 소울푸드는?", "ㅈㄱㄴ");
            Article article6 = new Article(6L, "당신의 취미는?", "댓글좀");

            Comment a = new Comment(1L, article4, "Park", "굳 윌 헌팅");
            Comment b = new Comment(4L, article5, "Park", "치킨");
            Comment c = new Comment(7L, article6, "Park", "조깅");
            List<Comment> expected = Arrays.asList(a, b, c);

            //비교
            assertEquals(expected.toString(), comments.toString());
        }

        /* kim 이라는 이름의 댓글을 모두 조회하는데 실패하는 거 중간에 park이 있음 */
        {
            String nickname = "kim";
            //예상
            List<Comment> comments = commentRepository.findByNickname(nickname);

            //실제
            Article article4 = new Article(4L, "당신의 인생영화는?", "댓글 ㄱ");
            Article article5 = new Article(5L, "당신의 소울푸드는?", "ㅈㄱㄴ");
            Article article6 = new Article(6L, "당신의 취미는?", "댓글좀");

            Comment a = new Comment(2L, article4, "kim", "굳 윌 헌팅");
            Comment b = new Comment(5L, article5, "kim", "치킨");
            Comment c = new Comment(7L, article6, "Park", "조깅");
            List<Comment> expected = Arrays.asList(a, b, c);

            //비교
            assertNotEquals(expected.toString(), comments.toString());
        }
    }
}