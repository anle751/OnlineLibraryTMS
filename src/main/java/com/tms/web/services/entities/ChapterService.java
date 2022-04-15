package com.tms.web.services.entities;

import com.tms.web.entities.library.Chapter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChapterService {
    public Chapter get(Long id);
    public Chapter save(Chapter chapter);
    public List<Chapter> findByChapterName(String name);
}
