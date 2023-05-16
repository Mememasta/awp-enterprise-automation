package ru.awp.enterprise.automation.exception;

public class ClientNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Client not found";
    public ClientNotFoundException() {
        super(MESSAGE);
    }
}
