package io.github.pedroermarinho.comandalivreapi.domain.exceptions;

import java.io.Serial;

public class CepInvalidException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CepInvalidException(String msg) {
        super(msg);
    }

    public CepInvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
