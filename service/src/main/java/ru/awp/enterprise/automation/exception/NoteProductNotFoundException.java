package ru.awp.enterprise.automation.exception;

public class NoteProductNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Не найден продукт принадлежащий записи";

    public NoteProductNotFoundException() {
        super(MESSAGE);
    }
}
