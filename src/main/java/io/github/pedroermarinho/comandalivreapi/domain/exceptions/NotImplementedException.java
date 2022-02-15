package io.github.pedroermarinho.comandalivreapi.domain.exceptions;

public class NotImplementedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NotImplementedException(){}

    public NotImplementedException(String msg) {
        super(msg);
    }

    public NotImplementedException(String msg, Throwable cause) {
        super(msg, cause);
    }

}