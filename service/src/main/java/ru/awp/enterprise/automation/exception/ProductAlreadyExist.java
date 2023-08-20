package ru.awp.enterprise.automation.exception;

public class ProductAlreadyExist extends RuntimeException {

    private static final String MESSAGE = "Продукт с name: \"%s\" уже существует";

    public ProductAlreadyExist(String name) {
        super(String.format(MESSAGE, name));
    }
}
