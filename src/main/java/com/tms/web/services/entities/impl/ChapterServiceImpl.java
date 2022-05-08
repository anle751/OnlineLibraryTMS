package com.tms.web.services.entities.impl;

import com.tms.web.entities.library.Chapter;
import com.tms.web.entities.repositories.ChapterRepository;
import com.tms.web.services.entities.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterRepository chapterRepository;

    @Override
    public Chapter get(Long id){
        return chapterRepository.findById(id).get();
    }

    @Override
    public Chapter save(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    @Override
    public List<Chapter> findByChapterName(String name) {
        return chapterRepository.findFirstByChapterName(name);
    }

}
