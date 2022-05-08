package com.tms.web.services.busines;

import com.kursx.parser.fb2.FictionBook;
import com.tms.web.entities.library.Book;
import org.springframework.stereotype.Service;

@Service
public interface ParseFB2BookService {
    public Book parse(FictionBook fb2);
}