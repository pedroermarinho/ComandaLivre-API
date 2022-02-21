package io.github.pedroermarinho.comandalivreapi.infra.services;

import org.springframework.stereotype.Component;

import io.github.pedroermarinho.comandalivreapi.domain.entities.CepEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.services.CepService;
import io.github.pedroermarinho.comandalivreapi.infra.drivers.CepDriver;

@Component
public class CepServiceImpl implements CepService {

    private final CepDriver cepDriver;

    public CepServiceImpl(CepDriver cepDriver) {
        this.cepDriver = cepDriver;
    }

    @Override
    public CepEntity findByCep(String cep) {
        return cepDriver.findByCep(cep).orElseThrow(
            () -> new ObjectNotFoundException(
                        "Cep não encontrado! Cep: " + cep + ", Tipo: " + CepEntity.class.getName()
                )
        );
    }
}
