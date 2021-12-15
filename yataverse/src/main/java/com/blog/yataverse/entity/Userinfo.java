package com.blog.yataverse.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity //DB가 인식가능
@Getter
@Setter
public class Userinfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //인덱스 자동생성
    private Long id;

    private String email;

    private String userpwd;

    private String useraddress;

    private int point;

    private String role;

}
