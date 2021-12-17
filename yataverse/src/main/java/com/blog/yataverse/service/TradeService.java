package com.blog.yataverse.service;

import com.blog.yataverse.entity.Sellinfo;
import com.blog.yataverse.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TradeService {
    private final TradeRepository repository;

    @Transactional
    public void saveSell(Sellinfo sell){
        log.info(sell.toString());
        repository.save(sell);
    }

    public List<Sellinfo> findAll(){
        List<Sellinfo> list = (List<Sellinfo>) repository.findAll();

        return list;
    }

}
