package io.github.pedroermarinho.comandalivreapi.infra.validation.username;

import io.github.pedroermarinho.comandalivreapi.domain.validation.UsernameValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidatorForm implements ConstraintValidator<UsernameValidationForm, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return new UsernameValidation().validation(value);
    }
}
