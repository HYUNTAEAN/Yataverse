package com.blog.yataverse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String main(){
        return "main";
    }

    @RequestMapping("/join")
    public String join(){
        return "join";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/goodbye")
    public String goodbye(Model model){
        model.addAttribute("name" , "너" );
        return "goodbye";
    }
}
