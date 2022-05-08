package com.tms.web.services.entities.impl;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.library.Author.Projections.AuthorInfo;
import com.tms.web.entities.repositories.AuthorRepository;
import com.tms.web.services.entities.AuthorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Override
    public Author get(Long id) {
        return authorRepository.getById(id);
    }

    @Override
    public Author save(Author author) {
        try {
            List<Author> authors = authorRepository.findByFirstNameAndSecName(author.getFirstName(), author.getSecName());
            if (authors.size()==0) {
                return authorRepository.save(author);
            }
        }catch (Exception e){
            log.fatal(this.getClass()+":error connection to DB ");
            return null;
        }
        return null;
    }

    @Override
    public AuthorInfo getAuthorInfo(Long id) {
        return authorRepository.getAuthorById(id);
    }

    @Override
    public List<Author> findByFirstNameAndSecName(String firstName, String secName) {
        return authorRepository.findByFirstNameAndSecName(firstName,secName);
    }

    @Override
    public Author findFirstByFirstNameAndSecName(String firstName, String secName) {
        return authorRepository.findFirstByFirstNameAndSecName(firstName,secName);
    }

}
