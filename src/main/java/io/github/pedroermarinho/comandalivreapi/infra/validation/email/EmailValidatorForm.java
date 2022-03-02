package io.github.pedroermarinho.comandalivreapi.infra.validation.email;

import io.github.pedroermarinho.comandalivreapi.domain.validation.EmailValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidatorForm implements ConstraintValidator<EmailValidationForm, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return new EmailValidation().validation(value);
    }
}
