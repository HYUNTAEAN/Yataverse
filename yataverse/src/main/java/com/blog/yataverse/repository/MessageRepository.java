package com.blog.yataverse.repository;

import com.blog.yataverse.entity.Messageinfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Messageinfo, Long> {
    List<Messageinfo> findByToEmail(String toEmail);

    int countByToEmail(String email);
}
