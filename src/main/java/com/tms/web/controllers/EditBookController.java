package com.tms.web.controllers;

import com.tms.web.entities.library.Book;
import com.tms.web.entities.library.Chapter;
import com.tms.web.services.busines.AddNewChapterService;
import com.tms.web.services.entities.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/library/editBook")
public class EditBookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AddNewChapterService addNewChapterService;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String getEditPage(@RequestParam("bookId") String bookId, Model model) {
        Book book = null;
        try {
            Long aLong = Long.valueOf(bookId);
            book = bookService.get(aLong);
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
        }
        model.addAttribute("book", book);
        return "bookEditPage";
    }

    @PostMapping("/addNewChapter")
    public String addChapterPage(@RequestParam("bookId") String bookId, Model model) {
        model.addAttribute("bookId", bookId);
        return "createChapterPage";
    }

    @PostMapping("/saveNewChapter")
    public RedirectView saveChapter(@RequestParam("bookIdTst") String bookId, Chapter chapter, RedirectAttributes attributes) {
        try {
            Long id = Long.valueOf(bookId);
            addNewChapterService.addNewChapter(id, chapter);
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
        }
        attributes.addAttribute("bookId", bookId);

        return new RedirectView("/library/editBook");
    }
}
