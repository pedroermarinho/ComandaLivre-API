package io.github.pedroermarinho.comandalivreapi.domain.validation;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.UsernameInvalidException;

public class UsernameValidation {
    private UsernameValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static Boolean validation(String username) {
        if (username == null)
            return false;
            
        return !EmailValidation.validation(username);
    }

    public static void validationThrow(String username) {
        if (Boolean.TRUE.equals(validation(username)))
            return;

        throw new UsernameInvalidException("Username Invalido !!!");
    }
}
