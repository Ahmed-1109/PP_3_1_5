package ru.kata.spring.boot_security.demo.exceptions;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException() {
    }

    public NoSuchUserException(String message) {
        super(message);
    }
}
