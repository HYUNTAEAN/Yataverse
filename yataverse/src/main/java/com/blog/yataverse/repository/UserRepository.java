package com.blog.yataverse.repository;

import com.blog.yataverse.entity.Userinfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Userinfo, Long> {


    Userinfo findUserByEmail(String email);

    boolean existsByEmail(String email);
}
