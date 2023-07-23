package ru.awp.enterprise.automation.exception;

public class ProductAlreadyExist extends RuntimeException {

    private static final String MESSAGE = "Продукт с id: \"%s\" уже существует";

    public ProductAlreadyExist(Long productId) {
        super(String.format(MESSAGE, productId));
    }
}
