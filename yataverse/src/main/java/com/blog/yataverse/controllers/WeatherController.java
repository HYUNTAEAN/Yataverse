package com.blog.yataverse.controllers;

import com.blog.yataverse.service.WeatherService;
import com.blog.yataverse.vo.WeatherVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@Slf4j
public class WeatherController {

    @Autowired
    private WeatherService service;

    @RequestMapping("/weather")
    public String weather(
            @RequestParam(value="where", defaultValue = "서울")
                    String where,
                    Model model) throws IOException {

        try {
            WeatherVO today = service.today(where);
            WeatherVO tomorrow = service.tomorrow(where);
            log.info(today.toString());
            log.info(tomorrow.toString());
            model.addAttribute("today", today);
            model.addAttribute("tomorrow", tomorrow);
        } catch(Exception e){

        }

        boolean isNumeric = where.matches("[+-]?\\d*(\\.\\d+)?");
        if(isNumeric == true && Integer.parseInt(where) < 13 && Integer.parseInt(where) > 0){
            where = "현재 지역";
        }

        model.addAttribute("locat", where);
        return "/weather";
    }
}
