package io.github.pedroermarinho.comandalivreapi.domain.validation;

import java.util.regex.Pattern;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.CepInvalidException;

public class CepValidation  implements Validation<String>{

    private final Pattern VALID_CEP_REGEX = Pattern.compile("[0-9]{5}[\\d]{3}", Pattern.CASE_INSENSITIVE); 

    @Override
    public  boolean validation(String cep){
        if(cep == null) return false;
        return VALID_CEP_REGEX.matcher(cep).find();
    }

    @Override
    public void validationThrow(String cep){
        if(Boolean.TRUE.equals(validation(cep))) return;
    
        throw new CepInvalidException("Cep Invalido !!!");
    }
    


}
