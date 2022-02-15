package io.github.pedroermarinho.comandalivreapi.domain.exceptions;

public class EmailInvalidException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EmailInvalidException(String msg) {
        super(msg);
    }

    public EmailInvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
