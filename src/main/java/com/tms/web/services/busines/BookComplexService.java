package com.tms.web.services.busines;

import com.tms.web.entities.library.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookComplexService {
    public Book save(Book book);
}
