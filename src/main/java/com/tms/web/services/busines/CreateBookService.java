package com.tms.web.services.busines;

import com.tms.web.entities.library.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CreateBookService {
    public void createBookForCurrUser(Book book, List<String> genresIds);
}
