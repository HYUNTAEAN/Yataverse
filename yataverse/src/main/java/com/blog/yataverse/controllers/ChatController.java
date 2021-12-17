package com.blog.yataverse.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@Slf4j
public class ChatController {

    @GetMapping("/chat")
    public String chat(HttpSession session){
//        String roomid = "1";
//
//        session.setAttribute("roomid", roomid);
        log.info("chat컨트롤러1");
        return "chat";
    }

    @GetMapping("/chat/info")
    public @ResponseBody
    String chat_reload(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("chat컨트롤러2");
        return "Success";
    }

    @PostMapping("/chat")
    public @ResponseBody
    String chat2(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("chat컨트롤러3");
        return "Success";
    }

    @GetMapping("/uname")
    public @ResponseBody String uname(Principal principal, HttpSession session){
        String loginId="";
        if(principal != null){
            loginId = principal.getName();
            String[] nick = loginId.split("@");
            loginId = nick[0];
            session.setAttribute("loginId", loginId);
        }
        return loginId;
    }
}
