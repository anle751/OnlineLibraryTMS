package com.tms.web.services.busines.impl;

import com.tms.web.entities.library.Book;
import com.tms.web.entities.repositories.BookRepository;
import com.tms.web.services.busines.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookSearchServiceImpl implements SearchService<Book> {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> search(String searchText) {
        return bookRepository.findByNameContaining( searchText);
    }
}
