package com.tdd.example.validator;

public interface MessageValidator<T> {

    void validate(T t);
}
