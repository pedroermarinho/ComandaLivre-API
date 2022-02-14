package io.github.pedroermarinho.comandalivreapi.domain.services;

import io.github.pedroermarinho.comandalivreapi.domain.Entities.Cep;

public interface CepService {
    Cep findByCep(String cep);
}
