package com.tms.web.controllers;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.security.User.projections.UserInfo;
import com.tms.web.services.busines.GetUserInfoService;
import com.tms.web.services.busines.RegisterAuthorService;
import com.tms.web.services.entities.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/userAccount")
public class UserAccountController {

    @Autowired
    private GetUserInfoService getUserInfoService;
    @Autowired
    private RegisterAuthorService registerAuthorService;

    @GetMapping
    public String getUserAccountPage(Model model) {
        UserInfo userInfo = null;
        boolean isAuthor = false;
        userInfo = getUserInfoService.get();
        if (Objects.nonNull(userInfo)) {
            isAuthor = Objects.nonNull(userInfo.getAuthor());
        }
        model.addAttribute("isAuthor", isAuthor);
        model.addAttribute("user", userInfo);
        return "userAccount";
    }

    @PostMapping("/registerAuthor")
    public String registerAuthor(Author author, BindingResult bindingResult, Model model) {
        UserInfo userInfo = null;
        boolean isAuthor = false;
        if (Objects.nonNull(author)) {
            userInfo = registerAuthorService.registerAuthor(author);
            if (Objects.nonNull(userInfo.getAuthor())) {
                isAuthor = true;
            }
        }
        model.addAttribute("isAuthor", isAuthor);
        model.addAttribute("user", userInfo);
        return "userAccount";
    }

}