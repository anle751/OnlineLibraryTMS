package com.tms.web.entities.repositories;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.library.Author.Projections.AuthorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    public List<AuthorInfo> findAllByFirstNameContaining(String name);
    public AuthorInfo getAuthorById(Long id);
    List<Author> findByFirstNameOrSecNameContaining(String searchFirstNameText, String searchSecNameText);
    public List<Author> findByFirstNameAndSecName(String firstName,String SecName);
    public Author findFirstByFirstNameAndSecName(String firstName,String SecName);
}
