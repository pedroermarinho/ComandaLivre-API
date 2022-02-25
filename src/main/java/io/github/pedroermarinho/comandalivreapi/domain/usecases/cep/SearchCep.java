package io.github.pedroermarinho.comandalivreapi.domain.usecases.cep;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.services.CepService;
import io.github.pedroermarinho.comandalivreapi.domain.validation.CepValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SearchCep {

    private final CepService cepService;

    public SearchCep(CepService cepService) {
        this.cepService = cepService;
    }

    public AddressDTO execute(String cep) {
        final List<Validation<String>> validations = Arrays.asList(new NotNullValidation<>(), new CepValidation());

        validations.forEach(validation -> validation.validationThrow(cep));

        return cepService.findByCep(cep);
    }
}
