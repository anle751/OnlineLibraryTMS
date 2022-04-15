package com.tms.web.services.validators;

import com.tms.web.entities.library.Book;

public class BookValidatorImpl implements Validator<Book> {

    @Override
    public boolean validate(Book book) {
        return false;
    }
}
