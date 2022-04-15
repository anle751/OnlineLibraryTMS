package com.tms.web.controllers;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.library.Book;
import com.tms.web.services.busines.SearchService;
import com.tms.web.services.entities.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private BookService bookService;
    @Autowired
    private SearchService<Book> bookSearchService;
    @Autowired
    private SearchService<Author> authorSearchService;

    @GetMapping
    public String getlibrary(Model model) {
        List<Book> all = bookService.getAll();
        model.addAttribute("books", all);
        return "library";
    }

    @PostMapping("/search")
    public String searchLibrary(@RequestParam(required = false, name = "searchText") String searchText, Model model) {
        List<Book> searchingBooks = bookSearchService.search(searchText);
        List<Author> searchingAuthors = authorSearchService.search(searchText);
        model.addAttribute("searchingBooksResult", searchingBooks);
        model.addAttribute("searchingAuthorsResult", searchingAuthors);
        model.addAttribute("searchText", searchText);
        return "searchResult";
    }

}
