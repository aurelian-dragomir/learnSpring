package com.learn.spring.entity.jdbc.one2many;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private Long postId;

    public CommentDto(String text, Long postId) {
        this.text = text;
        this.postId = postId;
    }
}
