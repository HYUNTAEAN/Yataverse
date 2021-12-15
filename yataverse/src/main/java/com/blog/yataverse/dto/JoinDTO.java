package com.blog.yataverse.dto;

import com.blog.yataverse.entity.userinfo;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class JoinDTO {

    private String userid;
    private String userpwd;
    private String email;
    private String useraddress;

    public userinfo toEntity() {return new userinfo(0, userid, userpwd, email, useraddress, 0);}
}
