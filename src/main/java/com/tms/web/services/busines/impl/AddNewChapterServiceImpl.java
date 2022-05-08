package com.tms.web.services.busines.impl;

import com.tms.web.entities.library.Book;
import com.tms.web.entities.library.Chapter;
import com.tms.web.services.busines.AddNewChapterService;
import com.tms.web.services.entities.BookService;
import com.tms.web.services.entities.ChapterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddNewChapterServiceImpl implements AddNewChapterService {
    @Autowired
    private BookService bookService;
    @Autowired
    private ChapterService chapterService;

    @Transactional
    @Override
    public void addNewChapter(Long bookId, Chapter chapter) {
        Book book = bookService.get(bookId);
        chapter.setBook(book);
        chapterService.save(chapter);
    }
}
