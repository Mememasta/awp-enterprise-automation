package ru.awp.enterprise.automation.exception;

public class AreaNotFoundException extends RuntimeException{

    private static final String MESSAGE = "Area not found";

    public AreaNotFoundException (){
        super(MESSAGE);
    }

}
