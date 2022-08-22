package io.github.pedroermarinho.comandalivreapi.domain.services;

import io.github.pedroermarinho.comandalivreapi.domain.record.AddressRecord;
import org.springframework.stereotype.Component;

@Component
public interface CepService {
    AddressRecord findByCep(String cep);
}
