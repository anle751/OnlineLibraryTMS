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
import java.util.Objects;

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
    public Book update(Long id, Book book) {
        Book bookDB = bookRepository.getById(id);
        if (Objects.nonNull(bookDB)){
            if (bookDB.getId() != null) {
                bookDB.setName(book.getName());
                bookDB.setDescription(book.getDescription());
                bookDB.setAuthor(book.getAuthor());
                bookDB.setGenres(book.getGenres());
                bookDB.setChapterList(book.getChapterList());
            }
        }
        return bookDB;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
