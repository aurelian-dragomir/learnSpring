package com.learn.spring.entity.jdbc.one2many;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;

    public PostDto(String title) {
        this.title = title;
    }
}
