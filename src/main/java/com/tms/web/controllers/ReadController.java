package com.tms.web.controllers;

import com.tms.web.entities.library.Book;
import com.tms.web.entities.library.Chapter;
import com.tms.web.services.busines.GetUserInfoService;
import com.tms.web.services.busines.ProgressUserLibraryService;
import com.tms.web.services.entities.BookService;
import com.tms.web.services.entities.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/library/read")
public class ReadController {

    @Autowired
    private BookService bookService;
    @Autowired
    private GetUserInfoService getUserInfoService;
    @Autowired
    private ProgressUserLibraryService progressUserLibraryService;

    @PostMapping()
    public String read(@RequestParam(name = "bookId") Long bookId, Model model) {
        Book book = bookService.get(bookId);
        Chapter chapter = progressUserLibraryService.checkProgress(bookId);
        model = getReadModel(book, chapter, model);
        return "readPage";
    }

    @PostMapping("/prev")
    public String getBefore(@RequestParam(name = "bookId") Long bookId,
                            @RequestParam(name = "chapterId") Long chapterId,
                            Model model) {
        Book book = bookService.get(bookId);
        Chapter chapter = progressUserLibraryService.updateProgress(bookId, chapterId, false);
        model = getReadModel(book, chapter, model);
        return "readPage";
    }

    @PostMapping("/next")
    public String getNext(@RequestParam(name = "bookId") Long bookId,
                          @RequestParam(name = "chapterId") Long chapterId,
                          Model model) {
        Book book = bookService.get(bookId);
        Chapter chapter = progressUserLibraryService.updateProgress(bookId, chapterId, true);
        model = getReadModel(book, chapter, model);
        return "readPage";
    }

    private Model getReadModel(Book book, Chapter chapter, Model model) {
        List<Chapter> chapterList = book.getChapterList();
        int chapterIndex = chapterList.indexOf(chapter);
        int size = chapterList.size();
        boolean hasNextChapter = chapterIndex < size - 1;
        boolean hasPrevChapter = chapterIndex > 0;
        model.addAttribute("user", getUserInfoService.get());
        model.addAttribute("hasNextChapter", hasNextChapter);
        model.addAttribute("hasPrevChapter", hasPrevChapter);
        model.addAttribute("book", book);
        model.addAttribute("chapter", chapter);
        return model;
    }
}
