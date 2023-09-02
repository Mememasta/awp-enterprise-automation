package ru.awp.enterprise.automation.exception;

public class ReportCardNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Не найдена запись в табеле";

    public ReportCardNotFoundException() {
        super(MESSAGE);
    }
}
