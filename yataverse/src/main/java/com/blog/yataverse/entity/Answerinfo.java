package com.blog.yataverse.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity //DB가 인식가능
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answerinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //인덱스 자동생성
    private Long id;

    private String email;

    private String questDesc;

    @ManyToOne
    @JoinColumn(name="Qusetinfo_id")
    private Questinfo questinfo;

}
