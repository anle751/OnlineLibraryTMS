package com.tms.web.services.validators.impl;

import com.tms.web.entities.library.Book;
import com.tms.web.entities.library.Chapter;
import com.tms.web.services.validators.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
@Log4j2
public class BookValidatorImpl implements Validator<Book> {

    @Override
    public boolean validate(Book book) {
        if (Objects.isNull(book)) {
            log.warn("Book is null");
            return false;
        }
        if (Objects.isNull(book.getAuthor())) {
            log.warn("Book Author is null");
            return false;
        }

        if (Objects.isNull(book.getName())){
            log.warn("Book:name is null");
            return false;
        }
        if (book.getName().isBlank()){
            log.warn("Book:name is blank");
            return false;
        }
        List<Chapter> chapterList = book.getChapterList();
        if (Objects.isNull(chapterList)){
            log.warn("Book:ChapterList is null");
            return false;
        }
        if (chapterList.size() == 0) {
            log.warn("Book:ChapterList size=0");
            return false;
        }
        return true;
    }
}
