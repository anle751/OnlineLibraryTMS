package com.tms.web.entities.repositories;

import com.tms.web.entities.library.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    public List<Book> findByName(String name);
    public List<Book> findAllByNameAndAuthorId(String name, Long id);
    public List<Book> findByNameContaining(String search);
}
