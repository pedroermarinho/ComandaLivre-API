package io.github.pedroermarinho.comandalivreapi.domain.usecases.cep;


import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.pedroermarinho.comandalivreapi.domain.entities.CepEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.CepInvalidException;
import io.github.pedroermarinho.comandalivreapi.domain.services.CepService;
import io.github.pedroermarinho.comandalivreapi.infra.drivers.CepDriver;
import io.github.pedroermarinho.comandalivreapi.infra.services.CepServiceImpl;

public class SearchCepTest {

    @Mock
    private CepDriver cepDriver;

    private CepService cepService;
    
    private SearchCep searchCep;

    @BeforeEach
    private void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

        cepService = new CepServiceImpl(cepDriver);

        searchCep = new SearchCep(cepService);

    }

    @Test
    void searchCepReturnsNewCepAddress() {
        
        when(cepDriver.findByCep(any(String.class))).thenReturn(Optional.of(new CepEntity()));

        assertInstanceOf(CepEntity.class, searchCep.execute("69088240"));
    }

    @Test
    void searchCepReturnsThrowCepInvelid() {
        assertThrows(CepInvalidException.class, ()->searchCep.execute("6908-8240"));
    }
}
