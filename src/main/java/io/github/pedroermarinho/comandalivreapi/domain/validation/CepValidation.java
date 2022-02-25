package io.github.pedroermarinho.comandalivreapi.domain.validation;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.CepInvalidException;

import java.util.regex.Pattern;

public class CepValidation implements Validation<String> {

    private final Pattern VALID_CEP_REGEX = Pattern.compile("[0-9]{5}[\\d]{3}", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean validation(String cep) {
        return VALID_CEP_REGEX.matcher(cep).find();
    }

    @Override
    public void validationThrow(String cep) {
        if (Boolean.TRUE.equals(validation(cep))) return;

        throw new CepInvalidException("Cep Invalido !!!");
    }


}
