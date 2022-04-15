package com.tms.web.services.entities.impl;

import com.tms.web.entities.library.UserLibrary;
import com.tms.web.entities.repositories.UserLibraryRepository;
import com.tms.web.services.entities.UserLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
@Service
public class UserLibraryServiceImpl implements UserLibraryService {
    @Autowired
    UserLibraryRepository userLibraryRepository;

    @Override
    public UserLibrary get(Long id) {
        Optional<UserLibrary> byId = userLibraryRepository.findById(id);
        return byId.get();
    }

    @Override
    public Boolean save(UserLibrary userLibrary) {
        return null;
    }

    @Transactional
    @Override
    public Boolean updateChapter(Long userLibraryId, Long chapterId) {
        UserLibrary byId = userLibraryRepository.getById(userLibraryId);
        if (Objects.nonNull(byId)) {
            byId.setChapterId(chapterId);
            return true;
        }
        return false;
    }

}
