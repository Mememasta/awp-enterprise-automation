package ru.awp.enterprise.automation.exception;

public class AreaAlreadyExists extends RuntimeException{
    private static final String MESSAGE = "Area already exists";

    public AreaAlreadyExists(){super(MESSAGE);}
}
