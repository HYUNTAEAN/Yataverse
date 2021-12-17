package com.blog.yataverse.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@Slf4j
public class MainController {

    @RequestMapping("/")
    public String main(Principal principal, HttpSession session){

        if(principal != null){
            String loginId = principal.getName();
            log.info(loginId);
            session.setAttribute("loginId", loginId);
        }
        return "main";
    }

    @RequestMapping("/access-denied")
    public String denied(){
        log.info("hey");
        return "/login";
    }

    @RequestMapping("/info")
    public String info(){
        return "/info";
    }



}
