package com.blog.yataverse.controllers;

import com.blog.yataverse.entity.Answerinfo;
import com.blog.yataverse.entity.Questinfo;
import com.blog.yataverse.service.QuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
public class QnaController {
    @Autowired
    private QuestService service;

    @GetMapping("/qna")
    public String qna(int idx, Model model){
        //index 값 들어오게 한다음
        log.info("도착");
        long count = service.count();
        List<Questinfo> list = service.findAll();

        if(count > 0){
            Questinfo q = list.get(idx);

            Long ansIdx = q.getId();
            List<Answerinfo> ansList = service.findAnswer(ansIdx);

            for(Answerinfo a : ansList){
                log.info(a.getQuestDesc());
            }

            model.addAttribute("ansList", ansList);
            model.addAttribute("questList", q);
        } else {
            model.addAttribute("questList", list);
        }
        return "/qna";
    }
    @RequestMapping("/quest")
    public String quest(){
        return "/question";
    }

    @PostMapping("/questProcess")
    public String question(String questName, String questDesc, Principal principal, Model model){

        String email = principal.getName();
        Questinfo quest = new Questinfo(0L, email, questName, questDesc);

        service.saveQuest(quest);

        int idx = 0;
        long count = service.count();
        List<Questinfo> list = service.findAll();

        if(count > 0){
            Questinfo q = list.get(idx);

            Long ansIdx = q.getId();
            List<Answerinfo> ansList = service.findAnswer(ansIdx);

            for(Answerinfo a : ansList){
                log.info(a.getQuestDesc());
            }

            model.addAttribute("ansList", ansList);
            model.addAttribute("questList", q);
        } else {
            model.addAttribute("questList", list);
        }

        return "redirect:/qna?idx=0";
    }

    @RequestMapping("/ansGo")
    public String ansGo(Long refId, String answerDesc, Model model, Principal principal){

        String email = principal.getName();
        String[] nick = email.split("@");
        email = nick[0];

        Questinfo quest = service.findOne(refId);

        Answerinfo answer = new Answerinfo(0L, email, answerDesc, quest);

        service.saveAnswer(answer);

        int idx = 0;
        long count = service.count();
        List<Questinfo> list = service.findAll();

        if(count > 0){
            Questinfo q = list.get(idx);

            Long ansIdx = q.getId();
            List<Answerinfo> ansList = service.findAnswer(ansIdx);

            for(Answerinfo a : ansList){
                log.info(a.getQuestDesc());
            }

            model.addAttribute("ansList", ansList);
            model.addAttribute("questList", q);
        } else {
            model.addAttribute("questList", list);
        }


        return "redirect:/qna?idx=0";
    }
}
