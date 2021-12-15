package com.blog.yataverse.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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



    @RequestMapping("/goodbye")
    public String goodbye(Model model){
        model.addAttribute("name" , "너" );
        return "goodbye";
    }
}
