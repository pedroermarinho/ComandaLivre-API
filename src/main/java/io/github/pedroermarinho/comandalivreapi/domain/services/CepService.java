package io.github.pedroermarinho.comandalivreapi.domain.services;

import org.springframework.stereotype.Component;

import io.github.pedroermarinho.comandalivreapi.domain.entities.CepEntity;

@Component
public interface CepService {
    CepEntity findByCep(String cep);
}
