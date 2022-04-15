package com.tms.web.entities.security.User.projections;

import com.tms.web.entities.library.Book;
import com.tms.web.entities.security.ROLE;
import com.tms.web.entities.security.User.User;

import java.util.Date;
import java.util.List;

public interface UserInfo {
    Long getId();

    String getUsername();

    String getNickName();

    List<ROLE> getRoles();

    AuthorInfo getAuthor();

    List<UserLibraryInfo> getUserLibrary();

    interface AuthorInfo {
        Long getId();

        String getFirstName();

        String getSecName();

        String getDescription();

        List<Book> getBooks();

        Date getCreation();

        Date getLastUpdate();

        Long getVersion();
    }

    interface UserLibraryInfo {
        Long getId();

        User getUser();

        Book getBook();

        Long getChapterId();
    }
}
