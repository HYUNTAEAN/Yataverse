package com.blog.yataverse.service;

import com.blog.yataverse.entity.Answerinfo;
import com.blog.yataverse.entity.Questinfo;
import com.blog.yataverse.repository.AnswerRepository;
import com.blog.yataverse.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestService {
    private final QuestRepository repository;
    private final AnswerRepository ansRepository;

    @Transactional
    public void saveQuest(Questinfo quest){
        log.info(quest.toString());
        repository.save(quest);
    }

    public List<Questinfo> findAll() {
        List<Questinfo> list = (List<Questinfo>) repository.findAll();
        return list;
    }

    public Questinfo findOne(Long refId) {
        Optional<Questinfo> result = repository.findById(refId);

        Questinfo quest = result.get();

        return quest;
    }

    public void saveAnswer(Answerinfo answer) {
        ansRepository.save(answer);
    }

    public List<Answerinfo> findAnswer(Long id) {
//        List<Long> longs = new ArrayList<>();
//        longs.add(id);

        List<Answerinfo> list = ansRepository.findByQuestinfoId(id);

        return list;
    }

    public long count() {
        return ansRepository.count();
    }
}
