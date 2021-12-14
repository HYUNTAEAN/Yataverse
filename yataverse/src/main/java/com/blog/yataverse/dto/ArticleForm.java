package com.blog.yataverse.dto;

import com.blog.yataverse.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private String title;
    private String content;

    public Article toEntity() {return new Article(null, title, content);}
}
