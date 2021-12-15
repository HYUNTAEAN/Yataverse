package com.blog.yataverse.controllers;

import com.blog.yataverse.entity.Userinfo;
import com.blog.yataverse.repository.UserRepository;
import com.blog.yataverse.service.ExService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private ExService service;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join")
    public String joinUser(Userinfo user){
        user.setRole("USER");
        service.joinUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/idchk")
    public @ResponseBody int idChk(String email){
        boolean result = userRepository.existsByEmail(email);
        int chk = 0;

        if(!result){
            chk = 1;
        }
        return chk;
    }


}
