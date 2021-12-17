package com.blog.yataverse.controllers;

import com.blog.yataverse.entity.Sellinfo;
import com.blog.yataverse.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class TradeController {
    @Autowired
    private TradeService service;

    @RequestMapping("/trade")
    public String trade(Model model){

        List<Sellinfo> list = service.findAll();

        model.addAttribute("sellList", list);

        return "/trade";
    }

    @GetMapping("/sell")
    public String sell(){
        return "/sell";
    }

    @PostMapping("/sellProcess")
    public String sellProcess(HttpSession session, String itemName, String itemDesc, String locat, Model model){


        String loca[] = locat.split(",");

        String lat = loca[0].substring(5);
        String lng = loca[1].substring(4,loca[1].length()-1);
        String email = (String) session.getAttribute("loginId");

        Sellinfo sell = new Sellinfo(0L, email, itemName, itemDesc, lat, lng);

        service.saveSell(sell);

        List<Sellinfo> list = service.findAll();

        model.addAttribute("sellList", list);
        log.info("좌표확인좀");

        return "/trade";
    }

}
