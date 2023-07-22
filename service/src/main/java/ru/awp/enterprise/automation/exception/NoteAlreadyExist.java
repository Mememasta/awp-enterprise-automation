package ru.awp.enterprise.automation.exception;

public class NoteAlreadyExist extends RuntimeException {

    private static final String MESSAGE = "note already exist";

    public NoteAlreadyExist() {
        super(MESSAGE);
    }
}
