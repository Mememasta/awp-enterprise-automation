package ru.awp.enterprise.automation.exception;

public class NoteNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Не найдена запись note";

    public NoteNotFoundException() {
        super(MESSAGE);
    }
}
