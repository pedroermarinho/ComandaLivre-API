package io.github.pedroermarinho.comandalivreapi.domain.usecases.cep;

import io.github.pedroermarinho.comandalivreapi.domain.entities.Cep;
import io.github.pedroermarinho.comandalivreapi.domain.services.CepService;

public class SearchCep {
    private final CepService cepService;

    public SearchCep(CepService cepService) {
        this.cepService = cepService;
    }

    public Cep execute(String cep){
        Cep.cepValidation(cep);
        return cepService.findByCep(cep);
    }
}
