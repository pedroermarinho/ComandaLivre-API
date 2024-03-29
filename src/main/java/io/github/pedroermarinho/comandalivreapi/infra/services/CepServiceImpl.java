package io.github.pedroermarinho.comandalivreapi.infra.services;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.record.AddressRecord;
import io.github.pedroermarinho.comandalivreapi.domain.services.CepService;
import io.github.pedroermarinho.comandalivreapi.infra.drivers.CepDriver;
import org.springframework.stereotype.Component;

@Component
public class CepServiceImpl implements CepService {

    private final CepDriver cepDriver;

    public CepServiceImpl(CepDriver cepDriver) {
        this.cepDriver = cepDriver;
    }

    @Override
    public AddressRecord findByCep(String cep) {
        return cepDriver.findByCep(cep).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Cep não encontrado! Cep: " + cep + ", Tipo: " + AddressRecord.class.getName()
                )
        );
    }
}
