package com.tms.web.services.entities;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.library.Author.Projections.AuthorInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    public Author get(Long id);
    public Author save(Author author);
    public AuthorInfo getAuthorInfo(Long id);
    public List<Author> findByFirstNameAndSecName(String firstName, String secName);
    public Author findFirstByFirstNameAndSecName(String firstName, String secName);
}
