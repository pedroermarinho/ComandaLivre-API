package io.github.pedroermarinho.comandalivreapi.domain.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class CepTest {
    

    @Test
    public void cepValidation(){
        assertDoesNotThrow(()->Cep.cepValidation("60440134"));
    }
}
