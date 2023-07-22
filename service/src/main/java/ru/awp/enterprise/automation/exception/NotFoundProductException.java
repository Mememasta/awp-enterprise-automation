package ru.awp.enterprise.automation.exception;

public class NotFoundProductException extends RuntimeException {

    private static final String MESSAGE = "Продукт не найден";

    public NotFoundProductException() {
        super(MESSAGE);
    }
}
