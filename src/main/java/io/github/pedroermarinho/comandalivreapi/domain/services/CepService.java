package io.github.pedroermarinho.comandalivreapi.domain.services;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public interface CepService {
    AddressDTO findByCep(String cep);
}
