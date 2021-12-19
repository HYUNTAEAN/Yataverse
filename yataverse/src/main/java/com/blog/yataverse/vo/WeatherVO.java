package com.blog.yataverse.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherVO {
    private String temp;
    private String sky;
    private String rainPer;
    private String wetPer;
    private String wind;
    private String dust;
    private String highDust;
    private String ray;
    private String sunset;

}
