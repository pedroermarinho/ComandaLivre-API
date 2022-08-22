package io.github.pedroermarinho.comandalivreapi.external.drivers;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.record.AddressRecord;
import io.github.pedroermarinho.comandalivreapi.infra.drivers.CepDriver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CepDriverImpl implements CepDriver {

    @Override
    public Optional<AddressRecord> findByCep(String cep) {
        throw new NotImplementedException();
    }
}
