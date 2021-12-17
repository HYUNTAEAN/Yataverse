package com.blog.yataverse.service;

import com.blog.yataverse.entity.Messageinfo;
import com.blog.yataverse.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageRepository repository;

    @Transactional
    public void msg(Messageinfo ms){
        repository.save(ms);
    }

    public List<Messageinfo> msgAll(String toEmail){
        List<Messageinfo> list = repository.findByToEmail(toEmail);
        
        return list;
    }

    @Transactional
    public void delMsg(Long id) {
        repository.deleteById(id);
    }
}
