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
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
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

        long qCount = service.qCount();
        long count = service.count();


        List<Questinfo> list = service.findAll(); //게시글 전부 가져옴
        if(list.size() < 1){
            return "/qna"; //게시글 미존재시 바로 리턴
        }
        
        Questinfo q = list.get(idx-1); //idx로 게시글 선택
        
        if(count > 0){ //댓글 존재여부 확인
            Long ansIdx = q.getId();
            List<Answerinfo> ansList = service.findAnswer(ansIdx);

            model.addAttribute("ansList", ansList);
            model.addAttribute("questList", q);
        } else {
            model.addAttribute("questList", q);
        }

        //페이징 처리
        int plus = 5;
        int pIdx = 1;

        ArrayList page = new ArrayList();
        if(qCount > 0) {
            pIdx = (((idx - 1) / 5) * 5) + 1;
        }

        if(qCount < pIdx+4){
            plus = (int)(qCount%5);
        }
        for(int i = pIdx; i < pIdx+plus; i++){
            page.add(i);
        }

        model.addAttribute("page", page);
        model.addAttribute("firstPage", pIdx);
        model.addAttribute("nowPage", idx);

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

        Long qCount = service.qCount();

        return "redirect:/qna?idx="+qCount;
    }

    @RequestMapping("/ansGo")
    public String ansGo(Long refId, String answerDesc, Model model, Principal principal){

        String email = principal.getName();
        String[] nick = email.split("@");
        email = nick[0];

        Questinfo quest = service.findOne(refId);

        Answerinfo answer = new Answerinfo(0L, email, answerDesc, quest);

        service.saveAnswer(answer);


        return "redirect:/qna?idx="+refId;
    }

    @RequestMapping("/delReply")
    public String delReply(Long id, Long idx){

        service.delReply(id);

        return "redirect:/qna?idx="+idx;
    }

    @RequestMapping("/pageChk")
    public @ResponseBody String pageChk(int page){
        long qCount = service.qCount();
        if(qCount > (page+4)){
            return "Success";
        }
        return "Fail";
    }

    @RequestMapping("/srchChk")
    public @ResponseBody String srchChk(int srch){
        long qCount = service.qCount();
        if(srch > qCount){
            return "Fail";
        }
        return "Success";
    }
}
