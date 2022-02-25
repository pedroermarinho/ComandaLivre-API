package io.github.pedroermarinho.comandalivreapi.domain.exceptions;

public class UsernameInvalidException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsernameInvalidException(String msg) {
        super(msg);
    }

    public UsernameInvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
