package com.example.demo.lessons.exceptions;

public class NoSelectEntityException extends RuntimeException {

    public NoSelectEntityException(String name) {
        super("No select entity of " + name);
    }
}
