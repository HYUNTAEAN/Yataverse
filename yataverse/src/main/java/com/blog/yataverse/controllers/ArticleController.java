package com.blog.yataverse.controllers;

import com.blog.yataverse.dto.ArticleForm;
import com.blog.yataverse.entity.Article;
import com.blog.yataverse.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("/article")
    public String article(){

        return "articles/new";
    }

    @RequestMapping("/article/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());

        //1. DTO -> Entity
        Article article = form.toEntity();
        System.out.println(article.toString());

        //2. Repo <-- Entity 전송
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
}
