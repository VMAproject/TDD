package com.tdd.example.parser;

public interface MessageParser<T> {

    T parse(String string);

}
