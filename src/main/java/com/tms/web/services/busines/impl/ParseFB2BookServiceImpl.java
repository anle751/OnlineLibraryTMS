package com.tms.web.services.busines.impl;

import com.kursx.parser.fb2.*;
import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.library.Book;
import com.tms.web.entities.library.Chapter;
import com.tms.web.entities.library.Genre.Genre;
import com.tms.web.services.busines.ParseFB2BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ParseFB2BookServiceImpl implements ParseFB2BookService {

    @Override
    public Book parse(FictionBook fb2) {
        Book book = null;
        List<Chapter> chapters = null;
        List<Genre> genres = null;
        Author author = null;
        String bookTitle = null;

        bookTitle = fb2.getDescription().getTitleInfo().getBookTitle();

        ArrayList<Person> authorsFB2 = fb2.getAuthors();

        if (Objects.nonNull(authorsFB2)) {
            if (authorsFB2.size() > 0) {
                author = Author.builder()
                        .firstName(authorsFB2.get(0).getFirstName())
                        .secName(authorsFB2.get(0).getLastName())
                        .build();
            }
        }

        ArrayList<String> genresFB2 = fb2.getDescription().getTitleInfo().getGenre();
        if (Objects.nonNull(genresFB2)) {
            genres = genresFB2.stream().map((a) -> Genre.builder().name(a).build()).collect(Collectors.toList());
        }

        Annotation annotationFB2 = fb2.getDescription().getTitleInfo().getAnnotation();
        String descriptionBook = null;
        if (Objects.nonNull(annotationFB2)) {
            descriptionBook = annotationFB2.getElements().stream().map((s) -> s.getText()).reduce("", String::concat);
        }

        ArrayList<Section> sectionsFB2 = fb2.getBody().getSections();
        if (Objects.nonNull(sectionsFB2)) {
            chapters = sectionsFB2.stream()
                    .map((a) -> Chapter.builder()
                            .chapterName(a.getTitles()
                                    .get(0).getParagraphs()
                                    .get(0).getText())
                            .chapterText(
                                    a.getElements().stream()
                                            .map(Element::getText)
                                            .collect(Collectors.joining())
                            )
                            .build()
                    )
                    .collect(Collectors.toList());
        }

        book = Book.builder()
                .author(author)
                .genres(genres)
                .description(descriptionBook)
                .chapterList(chapters)
                .name(bookTitle)
                .build();

        return book;
    }
}
