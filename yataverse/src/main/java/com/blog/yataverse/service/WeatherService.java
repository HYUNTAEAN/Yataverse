package com.blog.yataverse.service;

import com.blog.yataverse.vo.WeatherVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {


    public WeatherVO today(String where) throws IOException {

        String weatherUrl = "https://search.naver.com/search.naver?sm=top_hty&fbm=0&ie=utf8&query="+where+" 날씨";
        Document doc = Jsoup.connect(weatherUrl).get();
        Elements elem = doc.select(".weather_info");
        String[] str = elem.text().split(" ");

        WeatherVO today = new WeatherVO();
        today.setTemp(str[4]);
        today.setSky(str[2]);
        today.setRainPer(str[10]);
        today.setWetPer(str[12]);
        today.setWind(str[13]+' '+str[14]);
        today.setDust(str[16]);
        today.setHighDust(str[18]);
        today.setRay(str[20]);
        today.setSunset(str[22]);

        return today;
    }

    public WeatherVO tomorrow(String where) throws IOException {

        String weatherUrl = "https://search.naver.com/search.naver?sm=top_hty&fbm=0&ie=utf8&query="+where+" 날씨";
        Document doc = Jsoup.connect(weatherUrl).get();
        Elements elem = doc.select(".weather_info");
        String[] str = elem.text().split(" ");


        WeatherVO tomorrow = new WeatherVO();
        tomorrow.setTemp("오전 : " + str[28] + " / 오후 : " + str[39]);
        tomorrow.setSky("오전 : " + str[26] + " / 오후 : " + str[37]);
        tomorrow.setRainPer("오전 : " + str[31] + " / 오후 : " + str[42]);
        tomorrow.setDust("오전 : " + str[33] + " / 오후 : " + str[44]);
        tomorrow.setHighDust("오전 : " + str[35] + " / 오후 : " + str[46]);

        return tomorrow;
    }
}
