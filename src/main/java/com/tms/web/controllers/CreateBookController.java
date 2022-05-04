package com.tms.web.controllers;

import com.tms.web.entities.library.Book;
import com.tms.web.entities.library.Genre.Genre;
import com.tms.web.entities.library.Genre.GenreComparator;
import com.tms.web.exceptions.BookSavedException;
import com.tms.web.services.busines.CreateBookService;
import com.tms.web.services.entities.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/library/createBook")
public class CreateBookController {
    @Autowired
    private GenreService genreService;
    @Autowired
    private CreateBookService createBookService;

    @GetMapping
    public String getCreateBookPage(Model model) {
        Set<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);
        return "createBookPage";
    }

    @PostMapping
    public String saveBook(@RequestParam("idsChecked") List<String> ids, Book book) {
        createBookService.createBookForCurrUser(book, ids);
        return "redirect:/userAccount";
    }

    @ExceptionHandler(BookSavedException.class)
    public RedirectView saveBookException(BookSavedException bookSavedException, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("info",bookSavedException.getMessage());
        return new RedirectView("/userAccount");
    }

}
