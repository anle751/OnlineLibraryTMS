package com.tms.web.services.entities;

import com.tms.web.entities.library.Book;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    public Book get(Long id);
    public Book save(Book book);
    public Boolean update(Long id, Book book);
//    public List<Book> getByNameAndAuthor()
    public List<Book> getAll();
}
