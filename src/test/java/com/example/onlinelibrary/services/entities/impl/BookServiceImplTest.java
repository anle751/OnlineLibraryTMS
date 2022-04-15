package com.example.onlinelibrary.services.entities.impl;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.library.Book;
import com.tms.web.entities.library.Genre.Genre;
import com.tms.web.entities.repositories.BookRepository;
import com.tms.web.services.entities.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceImplTest {
//
//    @Mock
//    BookRepository bookRepository;
//    @InjectMocks
//    BookServiceImpl bookService;
//
//    @Test
//    void get() {
//        //given
//        Book testBook = Book.builder()
//                .name("testName")
//                .id(1L)
//                .chapterList(new ArrayList<>())
//                .description("testDescription")
//                .genres(List.of(
//                                Genre.builder()
//                                        .id(1L)
//                                        .name("genreName1")
//                                        .build(),
//                                Genre.builder()
//                                        .id(2L)
//                                        .name("genreName2")
//                                        .build()
//                        )
//                )
//                .author(
//                        Author.builder()
//                                .firstName("testFirstName")
//                                .secName("testSecName")
//                                .books(new ArrayList<>())
//                                .description("testDescription")
//                                .id(1L)
//                                .build())
//                .build();
//        when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(testBook));
//
//        //when
//        Book resultBook = bookService.get(1L);
//
//        //result
//        Assertions.assertEquals("testName", resultBook.getName());
//        Assertions.assertEquals(1L, resultBook.getId());
//        Assertions.assertEquals(0, resultBook.getChapterList().size());
//        Assertions.assertEquals(2, resultBook.getGenres().size());
//
//
//        Assertions.assertEquals("testFirstName", resultBook.getAuthor().getFirstName());
//        Assertions.assertEquals("testSecName", resultBook.getAuthor().getSecName());
//        Assertions.assertEquals(0, resultBook.getAuthor().getBooks().size());
//        Assertions.assertEquals(1L, resultBook.getAuthor().getId());
//
//    }
//
//    @Test
//    void saveNotExistBook() {
////        //given
////        when(bookRepository.findAllByNameAndAuthorId(Mockito.anyString(), Mockito.anyLong()))
////                .thenReturn(new ArrayList<>());
////        Book book = Book.builder()
////                .author(
////                        Author.builder()
////                                .id(1L)
////                                .build())
////                .name("test").build();
////        //when
////        Book result = bookService.save(book);
////
////        //result
////        Assertions.assertEquals(true, result);
//    }
//    @Test
//    void saveExistBook() {
////        //given
////
////        when(bookRepository.findAllByNameAndAuthorId(Mockito.anyString(), Mockito.anyLong()))
////                .thenReturn(List.of(new Book()));
////        Book book = Book.builder()
////                .author(
////                        Author.builder()
////                                .id(1L)
////                                .build())
////                .name("test").build();
////        //when
////        Boolean result = bookService.save(book);
////
////        //result
////        Assertions.assertEquals(false, result);
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void getAll() {
//    }
}