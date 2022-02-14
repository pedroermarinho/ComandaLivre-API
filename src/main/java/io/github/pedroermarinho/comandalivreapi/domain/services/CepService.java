package io.github.pedroermarinho.comandalivreapi.domain.services;

import org.springframework.stereotype.Component;

import io.github.pedroermarinho.comandalivreapi.domain.entities.Cep;

@Component
public interface CepService {
    Cep findByCep(String cep);
}
