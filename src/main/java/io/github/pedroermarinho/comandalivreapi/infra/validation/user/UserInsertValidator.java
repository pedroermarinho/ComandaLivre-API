package io.github.pedroermarinho.comandalivreapi.infra.validation.user;

import io.github.pedroermarinho.comandalivreapi.infra.forms.UserForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserForm> {
    @Override
    public boolean isValid(UserForm value, ConstraintValidatorContext context) {
        return true;
    }
}
