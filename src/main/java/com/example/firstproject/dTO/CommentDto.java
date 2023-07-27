package com.example.firstproject.dTO;

import com.example.firstproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto creatCommentDto(Comment a) {
        return new CommentDto(a.getId(),
                a.getArticle().getId(),
                a.getNickname(),
                a.getBody()
        );
    }
}
