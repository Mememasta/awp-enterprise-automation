package ru.awp.enterprise.automation.exception;

public class ProductNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Product not found by productId";

    public ProductNotFoundException() {
        super(MESSAGE);
    }
}
