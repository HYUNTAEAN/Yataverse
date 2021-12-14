package com.blog.yataverse.repository;

import com.blog.yataverse.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
