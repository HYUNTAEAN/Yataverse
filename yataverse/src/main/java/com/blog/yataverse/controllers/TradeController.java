package com.blog.yataverse.controllers;

import com.blog.yataverse.entity.Messageinfo;
import com.blog.yataverse.entity.Sellinfo;
import com.blog.yataverse.service.MessageService;
import com.blog.yataverse.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class TradeController {
    @Autowired
    private TradeService service;

    @Autowired
    private MessageService msgService;

    @RequestMapping("/trade")
    public String trade(Model model){

        List<Sellinfo> list = service.findAll();
        model.addAttribute("sellList", list);

        return "trade";
    }

    @GetMapping("/sell")
    public String sell(){
        return "sell";
    }

    @PostMapping("/sellProcess")
    public String sellProcess(Principal principal, String itemName, String itemDesc, String locat, Model model){


        String loca[] = locat.split(",");

        String lat = loca[0].substring(5);
        String lng = loca[1].substring(4,loca[1].length()-1);

        String email = principal.getName();

        Sellinfo sell = new Sellinfo(0L, email, itemName, itemDesc, lat, lng);

        service.saveSell(sell);

        List<Sellinfo> list = service.findAll();

        model.addAttribute("sellList", list);

        return "redirect:/trade";
    }

    @RequestMapping("/detail")
    public String detail(Principal principal, Model model, Long id){
        boolean chk = false;
        String loginId = principal.getName();

        Optional<Sellinfo> sell = service.findOne(id);
        Sellinfo se = sell.get();

        if(loginId.equals(se.getEmail())){
            chk = true;
        }

        model.addAttribute("sell" , se);
        model.addAttribute("chk" , chk);
        return "detail";
    }

    @PostMapping("/updateProcess")
    public String update(Long id, String itemName, String itemDesc, Model model){

        log.info(id.toString());
        log.info(itemName);
        log.info(itemDesc);

        Boolean chk = service.updateOne(id, itemName, itemDesc);

        List<Sellinfo> list = service.findAll();
        model.addAttribute("sellList", list);

        return "redirect:/trade";
    }

    @RequestMapping("/delete")
    public String delete(Long id, Model model){

        Boolean chk = service.deleteOne(id);

        List<Sellinfo> list = service.findAll();
        model.addAttribute("sellList", list);

        return "redirect:/trade";
    }

    @RequestMapping("/howtouse")
    public String howtouse(){
        return "howtouse";
    }

    @PostMapping("/buy")
    public String buy(String email, String buytext, Principal principal, Model model){

        if(buytext == ""){
            buytext = "I Love Yataverse!";
        }

        String myMail = principal.getName();
        String[] nick = myMail.split("@");
        myMail = nick[0];

        Messageinfo ms = new Messageinfo(0L,myMail,email,buytext);
        msgService.msg(ms);

        List<Sellinfo> list = service.findAll();
        model.addAttribute("sellList", list);

        return "trade";
    }

    @RequestMapping("/msgbox")
    public String msgbox(HttpSession session, Model model){
        String loginId = (String) session.getAttribute("loginId");

        List<Messageinfo> list = msgService.msgAll(loginId);
        if(list.size() > 0) {

            model.addAttribute("msgList", list);
        }
        return "msgbox";
    }

    @RequestMapping("/delMsg")
    public @ResponseBody void delMsg(Long id){
        msgService.delMsg(id);
    }

    @RequestMapping("/msgCount")
    public @ResponseBody int msgCount(Principal principal){
        String email = null;
        try {
            email = principal.getName();
        } catch(Exception e){

        }

        int count = 0;

        log.info(email);
        if(email != null){
            count = msgService.msgCount(email);
        }

        return count;
    }
}
