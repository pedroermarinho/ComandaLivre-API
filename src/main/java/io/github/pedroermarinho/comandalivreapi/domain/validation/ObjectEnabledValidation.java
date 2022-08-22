package io.github.pedroermarinho.comandalivreapi.domain.validation;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectDisabledException;

public class ObjectEnabledValidation implements Validation<Boolean> {

    @Override
    public boolean validation(Boolean value) {
        return value;
    }

    @Override
    public void validationThrow(Boolean value) {
        if (Boolean.TRUE.equals(validation(value))) return;
        throw new ObjectDisabledException("O valor está desativado!!!");
    }

}
