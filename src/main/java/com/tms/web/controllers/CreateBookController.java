package com.tms.web.controllers;

import com.tms.web.entities.library.Book;
import com.tms.web.entities.library.Genre.Genre;
import com.tms.web.entities.library.Genre.GenreComparator;
import com.tms.web.services.busines.CreateBookService;
import com.tms.web.services.entities.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/library/createBook")
public class CreateBookController {
    @Autowired
    private GenreService genreService;
    @Autowired
    private CreateBookService createBookService;

    @GetMapping
    public String getCreateBookPage(Model model) {
        List<Genre> genres = genreService.getAll();
        genres.sort(new GenreComparator());
        model.addAttribute("genres", genres);
        return "createBookPage";
    }

    @PostMapping
    public String saveBook(@RequestParam("idChecked") List<String> ids, Book book, Model model) {
        createBookService.createBookForCurrUser(book, ids);
        return "redirect:/userAccount";
    }

}
