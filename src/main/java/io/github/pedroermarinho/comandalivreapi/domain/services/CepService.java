package io.github.pedroermarinho.comandalivreapi.domain.services;

import org.springframework.stereotype.Component;

import io.github.pedroermarinho.comandalivreapi.domain.entities.AddressEntity;

@Component
public interface CepService {
    AddressEntity findByCep(String cep);
}
