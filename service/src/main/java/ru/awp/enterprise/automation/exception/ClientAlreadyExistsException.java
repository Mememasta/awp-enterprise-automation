package ru.awp.enterprise.automation.exception;

public class ClientAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE = "Client already exists";
    public ClientAlreadyExistsException() {
        super(MESSAGE);
    }

}
