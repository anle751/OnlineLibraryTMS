package com.tms.web.services.entities.impl;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.library.Book;
import com.tms.web.entities.library.Genre.Genre;
import com.tms.web.entities.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookServiceImpl bookService;

    @Test
    void get() {
        //given
        Book testBook = Book.builder().name("testName").id(1L).chapterList(new ArrayList<>()).description("testDescription").genres(List.of(Genre.builder().id(1L).name("genreName1").build(), Genre.builder().id(2L).name("genreName2").build())).author(Author.builder().firstName("testFirstName").secName("testSecName").books(new ArrayList<>()).description("testDescription").id(1L).build()).build();
        when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(testBook));

        //when
        Book resultBook = bookService.get(1L);

        //result
        Assertions.assertEquals("testName", resultBook.getName());
        Assertions.assertEquals(1L, resultBook.getId());
        Assertions.assertEquals(0, resultBook.getChapterList().size());
        Assertions.assertEquals(2, resultBook.getGenres().size());


        Assertions.assertEquals("testFirstName", resultBook.getAuthor().getFirstName());
        Assertions.assertEquals("testSecName", resultBook.getAuthor().getSecName());
        Assertions.assertEquals(0, resultBook.getAuthor().getBooks().size());
        Assertions.assertEquals(1L, resultBook.getAuthor().getId());
    }

    @Test
    void saveNotExistBook() {
        //given
        when(bookRepository.findAllByNameAndAuthorId(Mockito.anyString(), Mockito.anyLong())).thenReturn(new ArrayList<>());
        when(bookRepository.save(Mockito.any())).thenReturn(new Book());
        Book book = Book.builder().author(Author.builder().id(1L).build()).name("test").build();
        //when
        Book bookSaved = bookService.save(book);

        //result
        Assertions.assertNotNull(bookSaved);
    }

    @Test
    void saveExistBook() {
        //given

        when(bookRepository.findAllByNameAndAuthorId(Mockito.anyString(), Mockito.anyLong())).thenReturn(List.of(new Book()));
        Book book = Book.builder().author(Author.builder().id(1L).build()).name("test").build();
        //when
        Book savedBook = bookService.save(book);

        //result
        Assertions.assertNull(savedBook);
    }

    @Test
    void updateExistBook() {
        //given
        when(bookRepository.getById(Mockito.anyLong())).thenReturn(Book.builder().id(1L).name("test").description("testDescription").build());
        Book bookTest = Book.builder().author(Author.builder().id(1L).build()).name("test1").description("testDescription1").build();

        //when
        Book update = bookService.update(1L, bookTest);

        //result
        Assertions.assertNotNull(update);
        Assertions.assertEquals("test1", update.getName());
        Assertions.assertEquals("testDescription1", update.getDescription());
    }

    @Test
    void updateNotExistBook() {
        //given
        when(bookRepository.getById(Mockito.anyLong())).thenReturn(null);
        Book bookTest = Book.builder().author(Author.builder().id(1L).build()).name("test1").description("testDescription1").build();

        //when
        Book update = bookService.update(1L, bookTest);

        //result
        Assertions.assertNull(update);
    }

    @Test
    void getAll() {
        //given
        when(bookRepository.findAll()).thenReturn(List.of(new Book(), new Book()));
        //when
        List<Book> all = bookService.getAll();
        int size = all.size();
        //result
        Assertions.assertEquals(2, size);
    }
}