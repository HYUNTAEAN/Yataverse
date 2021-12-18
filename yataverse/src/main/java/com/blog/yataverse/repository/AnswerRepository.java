package com.blog.yataverse.repository;

import com.blog.yataverse.entity.Answerinfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answerinfo, Long> {

    List<Answerinfo> findByQuestinfoId(Long longs);
}
