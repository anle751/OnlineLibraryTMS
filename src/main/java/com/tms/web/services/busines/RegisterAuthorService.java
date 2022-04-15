package com.tms.web.services.busines;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.security.User.projections.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface RegisterAuthorService {
    public UserInfo registerAuthor(Author author);
}
