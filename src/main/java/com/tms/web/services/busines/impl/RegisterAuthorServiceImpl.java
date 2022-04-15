package com.tms.web.services.busines.impl;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.security.User.User;
import com.tms.web.entities.security.User.projections.UserInfo;
import com.tms.web.services.busines.GetUserInfoService;
import com.tms.web.services.busines.RegisterAuthorService;
import com.tms.web.services.entities.AuthorService;
import com.tms.web.services.entities.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterAuthorServiceImpl implements RegisterAuthorService {
    @Autowired
    UserService userService;
    @Autowired
    AuthorService authorService;
    @Autowired
    GetUserInfoService getUserInfoService;
    @Transactional
    @Override
    public UserInfo registerAuthor(Author author) {
        UserInfo userInfo = getUserInfoService.get();
        User user = userService.get(userInfo.getId());
        authorService.save(author);
        user.setAuthor(author);
        return userInfo;
    }
}
