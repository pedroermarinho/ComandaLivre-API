package io.github.pedroermarinho.comandalivreapi.infra.validation.email;

import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.SearchUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniqueValidatorForm implements ConstraintValidator<EmailUniqueValidationForm, String> {

    private final SearchUser searchUser;

    public EmailUniqueValidatorForm(SearchUser searchUser) {
        this.searchUser = searchUser;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !searchUser.existsByEmail(value);
    }
}
