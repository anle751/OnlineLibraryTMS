package com.tms.web.services.entities;

import com.tms.web.entities.library.UserLibrary;
import org.springframework.stereotype.Service;

@Service
public interface UserLibraryService {
    public UserLibrary get(Long id);
    public Boolean save(UserLibrary userLibrary);

    public Boolean updateChapter(Long userLibraryId, Long chapterId);

}
