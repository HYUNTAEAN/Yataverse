package com.blog.yataverse.entity;

import javax.persistence.*;

@Entity //DB가 인식가능
public class userinfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //인덱스 자동생성
    private int id;
    
    @Column
    private String userid;
    
    @Column
    private String userpwd;

    @Column
    private String email;

    @Column
    private String useraddress;

    @Column
    private int point;

    public userinfo(int id, String userid, String userpwd, String email, String useraddress, int point) {
        this.id = id;
        this.userid = userid;
        this.userpwd = userpwd;
        this.email = email;
        this.useraddress = useraddress;
        this.point = point;
    }

    @Override
    public String toString() {
        return "Join{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", email='" + email + '\'' +
                ", useraddress='" + useraddress + '\'' +
                ", point=" + point +
                '}';
    }
}
