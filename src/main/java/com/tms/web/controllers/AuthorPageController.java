package com.tms.web.controllers;

import com.tms.web.entities.library.Author.Projections.AuthorInfo;
import com.tms.web.services.busines.GetUserInfoService;
import com.tms.web.services.entities.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequestMapping("/library/author")
public class AuthorPageController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private GetUserInfoService getUserInfoService;

    @PostMapping
    public String getAuthor(@RequestParam(name = "authorId") String authorId, Model model) {
        Long longId = null;
        AuthorInfo authorInfo = null;
        try {
            longId = Long.valueOf(authorId);
            authorInfo = authorService.getAuthorInfo(longId);
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
        }
        if (Objects.nonNull(authorInfo)) {
            model.addAttribute("author", authorInfo);
        }
        model.addAttribute("user", getUserInfoService.get());
        return "authorPage";
    }
}
