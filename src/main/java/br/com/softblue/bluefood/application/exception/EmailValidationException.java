package br.com.softblue.bluefood.application.exception;

public class EmailValidationException extends RuntimeException {

    public EmailValidationException(String message) {
        super(message);
    }

}
