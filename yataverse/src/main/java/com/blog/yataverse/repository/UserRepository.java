package com.blog.yataverse.repository;

import com.blog.yataverse.entity.userinfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<userinfo, Integer> {

    boolean existsByuserid(String userid);

}
