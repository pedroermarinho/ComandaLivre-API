package io.github.pedroermarinho.comandalivreapi.external.drivers;

import java.util.Optional;
import io.github.pedroermarinho.comandalivreapi.domain.entities.Cep;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.infra.drivers.CepDriver;

public class CepDriverImpl implements CepDriver {

    @Override
    public Optional<Cep> findByCep(String cep) {
        throw new NotImplementedException() ;
    }
}
