package br.com.softblue.bluefood.application;

public class ApplicationServiceException extends RuntimeException {

    public ApplicationServiceException() {
    }

    public ApplicationServiceException(String message) {
        super(message);
    }

    public ApplicationServiceException(Throwable cause) {
        super(cause);
    }

}
