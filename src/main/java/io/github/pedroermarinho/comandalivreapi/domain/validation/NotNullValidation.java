package io.github.pedroermarinho.comandalivreapi.domain.validation;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotNullException;

public class NotNullValidation<T> implements Validation<T> {

    @Override
    public boolean validation(T value) {
        return value != null;
    }

    @Override
    public void validationThrow(T value) {
        if (Boolean.TRUE.equals(validation(value))) return;

        throw new NotNullException("O valor não pode ser nulo!!!");
    }

}
