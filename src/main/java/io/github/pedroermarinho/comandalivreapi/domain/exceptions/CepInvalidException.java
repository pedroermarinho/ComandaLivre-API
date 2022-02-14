package io.github.pedroermarinho.comandalivreapi.domain.exceptions;

public class CepInvalidException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CepInvalidException(String msg) {
        super(msg);
    }

    public CepInvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
