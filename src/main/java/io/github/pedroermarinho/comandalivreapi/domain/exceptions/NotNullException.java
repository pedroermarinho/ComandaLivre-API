package io.github.pedroermarinho.comandalivreapi.domain.exceptions;

public class NotNullException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotNullException(String msg) {
        super(msg);
    }

    public NotNullException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
