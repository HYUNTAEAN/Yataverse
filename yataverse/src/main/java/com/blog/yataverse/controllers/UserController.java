package com.blog.yataverse.controllers;

import com.blog.yataverse.dto.JoinDTO;
import com.blog.yataverse.entity.userinfo;
import com.blog.yataverse.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/join/create")
    public String createArticle(JoinDTO form){
        log.info(form.toString());

        //1. DTO -> Entity
        userinfo join = form.toEntity();
        System.out.println(join.toString());

        //2. Repo <-- Entity 전송
        userRepository.save(join);
        log.info("success");
        //System.out.println("saved"+saved.toString());
        return "main";
    }

    @RequestMapping("/idchk")
    public @ResponseBody int idChk(String userid){
        boolean result = userRepository.existsByuserid(userid);
        int chk = 0;

        if(!result){
            chk = 1;
        }
        return chk;
    }


}
