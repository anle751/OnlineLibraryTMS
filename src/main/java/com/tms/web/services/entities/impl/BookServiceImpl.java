package com.tms.web.services.entities.impl;

import com.tms.web.entities.library.Book;
import com.tms.web.entities.repositories.BookRepository;
import com.tms.web.services.entities.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public Book get(Long id) {
        Book book = bookRepository.findById(id).get();
        return book;
//        return bookRepository.getById(id);
    }

    @Override
    public Book save(Book book) {
        List<Book> allByNameAndAuthorId = bookRepository
                .findAllByNameAndAuthorId(book.getName(), book.getAuthor().getId());
        if (allByNameAndAuthorId.size() == 0) {
            return bookRepository.save(book);
        }
        return null;
    }

    @Override
    @Modifying
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean update(Long id, Book book) {
        Book byId = bookRepository.getById(id);
        if (byId.getId() != null) {
            byId.setAuthor(book.getAuthor());
            byId.setGenres(book.getGenres());
            byId.setChapterList(book.getChapterList());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
