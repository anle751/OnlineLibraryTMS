package com.tms.web.controllers;

import com.tms.web.entities.library.Book;
import com.tms.web.entities.library.Chapter;
import com.tms.web.exceptions.BadIdException;
import com.tms.web.services.busines.AddNewChapterService;
import com.tms.web.services.entities.BookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/library/editBook")
@Log4j2
public class EditBookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AddNewChapterService addNewChapterService;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String getEditPage(@RequestParam("bookId") String bookId, @RequestParam(value = "info",required = false) String info, Model model) {
        Book book = null;
        Long id;
        try{
            id=Long.valueOf(bookId);
        }catch (NumberFormatException nfe){
            throw new BadIdException("book cannot be changed");
        }
        book = bookService.get(id);

        model.addAttribute("book", book);
        model.addAttribute("info",info);
        return "bookEditPage";
    }

    @PostMapping("/addNewChapter")
    public String addChapterPage(@RequestParam("bookId") String bookId, Model model) {
        model.addAttribute("bookId", bookId);
        return "createChapterPage";
    }

    @PostMapping("/saveNewChapter")
    public RedirectView saveChapter(@RequestParam("bookIdTst") String bookId, Chapter chapter, RedirectAttributes attributes) {
        Long id;
        try {
            id = Long.valueOf(bookId);
        }catch (NumberFormatException nfe) {
            throw  new BadIdException("chapter not saved");
        }
            addNewChapterService.addNewChapter(id, chapter);
        attributes.addAttribute("bookId", bookId);

        return new RedirectView("/library/editBook");
    }
    @ExceptionHandler(BadIdException.class)
    public RedirectView badIdExceptionHandler(RedirectAttributes attributes, BadIdException badIdException){
        log.warn("badIDException:"+badIdException.getMessage());
        attributes.addAttribute("info", "internal error:"+badIdException.getMessage());
        return new RedirectView("/userAccount");
    }
}
