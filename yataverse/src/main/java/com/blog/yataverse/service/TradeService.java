package com.blog.yataverse.service;

import com.blog.yataverse.entity.Sellinfo;
import com.blog.yataverse.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Optional<Sellinfo> findOne(Long id) {
        Optional<Sellinfo> sell = repository.findById(id);

        return sell;
    }

    public boolean updateOne(Long id, String itemName, String itemDesc) {
        Optional<Sellinfo> sell = repository.findById(id);
        boolean result = true;
        sell.ifPresent(sellinfo -> {
            sellinfo.setItemName(itemName);
            sellinfo.setItemDesc(itemDesc);

            repository.save(sellinfo);
        });
        return result;
    }

    public Boolean deleteOne(Long id) {
        Optional<Sellinfo> sell = repository.findById(id);
        boolean result = true;
        sell.ifPresent(sellinfo -> {
            repository.delete(sellinfo);
        });
        return result;
    }
}
