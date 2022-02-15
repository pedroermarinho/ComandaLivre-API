package io.github.pedroermarinho.comandalivreapi.domain.usecases.cep;

import org.springframework.stereotype.Service;

import io.github.pedroermarinho.comandalivreapi.domain.entities.Cep;
import io.github.pedroermarinho.comandalivreapi.domain.services.CepService;
import io.github.pedroermarinho.comandalivreapi.domain.validation.CepValidation;

@Service
public class SearchCep {
    private final CepService cepService;

    public SearchCep(CepService cepService) {
        this.cepService = cepService;
    }

    public Cep execute(String cep){
        CepValidation.validationThrow(cep);
        return cepService.findByCep(cep);
    }
}
