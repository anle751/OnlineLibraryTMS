package com.tms.web.controllers;

import com.tms.web.entities.library.Book;
import com.tms.web.exceptions.UploadFileException;
import com.tms.web.services.busines.UploadBookService;
import com.tms.web.services.validators.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Log4j2
@Controller
@RequestMapping("/userAccount/adminFunctional")
public class AdminFunctionalController {
    @Autowired
    private UploadBookService uploadBookService;
    @Autowired
    private Validator<MultipartFile> multipartFileValidator;

    @PostMapping
    public String uploadFile(@RequestParam(name = "file") MultipartFile file) throws IOException {
        if (multipartFileValidator.validate(file)) {
            uploadBookService.upload(file);
        }
        return "redirect:/userAccount";
    }

    @ExceptionHandler(IOException.class)
    public void test(Exception e) {
        log.fatal(e.getMessage());
    }

    @ExceptionHandler({UploadFileException.class, NullPointerException.class})
    public RedirectView uploadExceptionHandler(UploadFileException ex, RedirectAttributes attributes){
        attributes.addAttribute("info",ex.getMessage());
        return new RedirectView("/userAccount");
    }
}
