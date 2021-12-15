package com.blog.yataverse.service;

import com.blog.yataverse.dto.MyUserDetail;
import com.blog.yataverse.entity.Userinfo;
import com.blog.yataverse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExService implements UserDetailsService {
    private final UserRepository repository;

    @Transactional
    public void joinUser(Userinfo user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUserpwd(passwordEncoder.encode(user.getUserpwd()));
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //여기서 받은 유저 패스워드와 비교하여 로그인 인증
        Userinfo user = repository.findUserByEmail(email);
        return new MyUserDetail(user);
    }
}
