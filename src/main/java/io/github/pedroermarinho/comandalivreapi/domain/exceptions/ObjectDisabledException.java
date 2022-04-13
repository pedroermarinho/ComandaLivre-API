package io.github.pedroermarinho.comandalivreapi.domain.exceptions;

public class ObjectDisabledException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjectDisabledException(String msg) {
        super(msg);
    }

    public ObjectDisabledException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
