package com.tms.web.services.busines.impl;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.library.Book;
import com.tms.web.entities.library.Genre.Genre;
import com.tms.web.entities.security.User.projections.UserInfo;
import com.tms.web.services.busines.CreateBookService;
import com.tms.web.services.busines.GetUserInfoService;
import com.tms.web.services.entities.AuthorService;
import com.tms.web.services.entities.BookService;
import com.tms.web.services.entities.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateBookServiceImpl implements CreateBookService {
    @Autowired
    GenreService genreService;
    @Autowired
    BookService bookService;
    @Autowired
    AuthorService authorService;
    @Autowired
    GetUserInfoService getUserInfoService;

    @Transactional
    @Override
    public void createBookForCurrUser(Book book, List<String> genresIds) {
        try {
            List<Long> collect = genresIds.stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            List<Genre> byIds = genreService.findByIds(collect);
            UserInfo userInfo = getUserInfoService.get();
            Long id = userInfo.getAuthor().getId();
            Author author = authorService.get(id);
            book.setAuthor(author);
            book.setGenres(byIds);
            bookService.save(book);
        }catch (NumberFormatException nfe){
            System.out.println(nfe.getMessage());
        }
    }
}
