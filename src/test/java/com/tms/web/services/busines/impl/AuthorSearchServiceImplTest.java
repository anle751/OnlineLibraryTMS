package com.tms.web.services.busines.impl;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.repositories.AuthorRepository;
import com.tms.web.services.entities.AuthorService;
import com.tms.web.services.entities.impl.AuthorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorSearchServiceImplTest {

    @Mock
    AuthorRepository authorRepository;
    @InjectMocks
    AuthorSearchServiceImpl authorSearchService;

    @Test
    void searchExistAuthors() {
        //given
        Mockito.when(authorRepository.findByFirstNameOrSecNameContaining("test","test"))
                .thenReturn(List.of(new Author(),new Author()));
        //when
        List<Author> searchAuthors = authorSearchService.search("test");
        int size=searchAuthors.size();

        //result
        Assertions.assertEquals(2,size);
    }

    @Test
    void searchNotExistAuthors() {
        //given
        Mockito.when(authorRepository.findByFirstNameOrSecNameContaining("test","test"))
                .thenReturn(List.of(new Author(),new Author()));
        Mockito.when(authorRepository.findByFirstNameOrSecNameContaining(Mockito.anyString(),Mockito.anyString()))
                .thenReturn(new ArrayList<>());
        //when
        List<Author> searchAuthors = authorSearchService.search("test1");
        int size= searchAuthors.size();

        //result
        Assertions.assertEquals(0,size);
    }
}