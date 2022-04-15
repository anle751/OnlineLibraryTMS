package com.tms.web.entities.library.Author.Projections;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.library.Chapter;
import com.tms.web.entities.library.Genre.Genre;

import java.util.Date;
import java.util.List;

public interface AuthorInfo {
    Long getId();

    String getFirstName();

    String getSecName();

    String getDescription();

    List<BookInfo> getBooks();

    interface BookInfo {
        Long getId();

        String getName();

        String getDescription();

        String getImage();

        Author getAuthor();

        List<Genre> getGenres();

        List<Chapter> getChapterList();

        Date getCreation();

        Date getLastUpdate();

        Long getVersion();
    }
}
