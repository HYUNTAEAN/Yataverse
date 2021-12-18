package com.blog.yataverse.repository;

import com.blog.yataverse.entity.Questinfo;
import org.springframework.data.repository.CrudRepository;

public interface QuestRepository extends CrudRepository<Questinfo, Long> {

}
