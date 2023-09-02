package ru.awp.enterprise.automation.exception;

public class UserDeleteException extends RuntimeException {

    private static final String MESSAGE = "Не найден работник для удаления из табеля";

    public UserDeleteException() {
        super(MESSAGE);
    }
}
