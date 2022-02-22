package io.github.pedroermarinho.comandalivreapi.external.drivers;

import java.util.Optional;

import org.springframework.stereotype.Component;

import io.github.pedroermarinho.comandalivreapi.domain.entities.AddressEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.infra.drivers.CepDriver;

@Component
public class CepDriverImpl implements CepDriver {

    @Override
    public Optional<AddressEntity> findByCep(String cep) {
        throw new NotImplementedException() ;
    }
}
