package com.tms.web.services.busines.impl;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.repositories.AuthorRepository;
import com.tms.web.services.busines.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorSearchServiceImpl implements SearchService<Author> {

    @Autowired
    AuthorRepository authorRepository;
    @Override
    public List<Author> search(String searchText) {
        return authorRepository.findByFirstNameOrSecNameContaining(searchText,searchText);
    }
}
