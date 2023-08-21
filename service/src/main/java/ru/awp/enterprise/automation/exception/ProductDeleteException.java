package ru.awp.enterprise.automation.exception;

public class ProductDeleteException extends RuntimeException {

    private static final String MESSAGE = "Не найдены продукты для удаления";

    public ProductDeleteException() {
        super(MESSAGE);
    }
}
