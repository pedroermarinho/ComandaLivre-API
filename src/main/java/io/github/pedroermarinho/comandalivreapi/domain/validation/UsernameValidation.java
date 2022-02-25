package io.github.pedroermarinho.comandalivreapi.domain.validation;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.UsernameInvalidException;

public class UsernameValidation implements Validation<String> {

    @Override
    public boolean validation(String username) {
        return !new EmailValidation().validation(username);
    }

    @Override
    public void validationThrow(String username) {
        if (Boolean.TRUE.equals(validation(username)))
            return;

        throw new UsernameInvalidException("Username Invalido !!!");
    }
}
