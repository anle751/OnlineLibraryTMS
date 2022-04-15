package com.tms.web.services.busines.impl;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.library.Book;
import com.tms.web.entities.library.Chapter;
import com.tms.web.entities.library.Genre.Genre;
import com.tms.web.entities.repositories.GenreRepository;
import com.tms.web.services.busines.BookComplexService;
import com.tms.web.services.entities.AuthorService;
import com.tms.web.services.entities.BookService;
import com.tms.web.services.entities.ChapterService;
import com.tms.web.services.entities.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class BookComplexServiceImpl implements BookComplexService {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private GenreRepository genreRepository;

    @Override
    @Transactional
    @Modifying
    public Book save(Book book) {
        Author savedAuthor = authorService.save(book.getAuthor());

        if (Objects.isNull(savedAuthor)) {
            List<Author> authorsDB = authorService.findByFirstNameAndSecName(book.getAuthor().getFirstName(), book.getAuthor().getSecName());
            savedAuthor = authorsDB.get(0);
            book.setAuthor(savedAuthor);
        }

        Book savedBook = bookService.save(book);
        if (Objects.nonNull(savedBook)) {
            if (Objects.nonNull(savedAuthor)) {
                savedAuthor.setBooks(List.of(savedBook));
            } else {
                Author author = authorService.findByFirstNameAndSecName(book.getAuthor().getFirstName(),
                        book.getAuthor().getSecName()).get(0);
                author.getBooks().add(book);
            }

            List<Genre> genres = book.getGenres();
            for (Genre genre : genres) {

                int i = genres.indexOf(genre);
                Genre genreSaved = genreService.save(genre);
                if (Objects.nonNull(genreSaved)) {
                    genreSaved.setBooks(List.of(book));
                } else {
                    Genre genreDb = genreService.findByName(genre.getName()).get(0);
                    book.getGenres().set(i,genreDb);
                    System.out.println("");
                }
            }

            List<Chapter> chapterList = book.getChapterList();
            for (Chapter chapter : chapterList) {
                Chapter saved = chapterService.save(chapter);
                saved.setBook(savedBook);
            }

        }

        return savedBook;
    }
}
