package com.tms.web.entities.repositories;

import com.tms.web.entities.library.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter,Long> {
    public List<Chapter> findFirstByChapterName(String name);

}
