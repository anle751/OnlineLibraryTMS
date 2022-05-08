package com.tms.web.services.busines;

import com.tms.web.entities.library.Chapter;
import org.springframework.stereotype.Service;

@Service
public interface AddNewChapterService {
    public void addNewChapter(Long bookId, Chapter chapter);
}
