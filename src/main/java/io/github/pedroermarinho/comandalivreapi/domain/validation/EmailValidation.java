package io.github.pedroermarinho.comandalivreapi.domain.validation;

import java.util.regex.Pattern;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.UsernameInvalidException;

public class EmailValidation {
    private EmailValidation() {
        throw new IllegalStateException("Utility class");
    }

    private static final Pattern VALID_EMAIL_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    public static Boolean validation(String email) {
        if (email == null)
            return false;
            
        return VALID_EMAIL_REGEX.matcher(email).find();
    }

    public static void validationThrow(String email) {
        if (Boolean.TRUE.equals(validation(email)))
            return;

        throw new UsernameInvalidException("Email Invalido !!!");
    }
}
