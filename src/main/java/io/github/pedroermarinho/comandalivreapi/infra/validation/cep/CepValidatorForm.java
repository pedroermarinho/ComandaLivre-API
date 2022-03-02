package io.github.pedroermarinho.comandalivreapi.infra.validation.cep;

import io.github.pedroermarinho.comandalivreapi.domain.validation.CepValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CepValidatorForm implements ConstraintValidator<CepValidationForm, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return new CepValidation().validation(value);
    }
}
