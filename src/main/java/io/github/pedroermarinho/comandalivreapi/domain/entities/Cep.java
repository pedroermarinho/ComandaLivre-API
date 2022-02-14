package io.github.pedroermarinho.comandalivreapi.domain.entities;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.CepInvalidException;

public class Cep {


    public static void cepValidation(String cep){
        if(!cep.matches("[0-9]{5}[\\d]{3}")){
            throw new CepInvalidException("Cep Invalido !!!");
        }
    }

}
