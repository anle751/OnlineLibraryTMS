package com.tms.web.services.validators;

import org.springframework.stereotype.Service;

@Service
public interface Validator<T> {
    public boolean validate(T obj);
}
