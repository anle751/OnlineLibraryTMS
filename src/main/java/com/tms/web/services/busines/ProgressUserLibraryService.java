package com.tms.web.services.busines;

import com.tms.web.entities.library.Chapter;

public interface ProgressUserLibraryService {
    public Chapter checkProgress(Long bookId);
    public Chapter updateProgress(Long bookId, Long chapterId, Boolean nextPrev);
}
