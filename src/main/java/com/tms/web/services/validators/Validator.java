package com.tms.web.services.validators;

public interface Validator<T> {
    public boolean validate(T obj);
}
