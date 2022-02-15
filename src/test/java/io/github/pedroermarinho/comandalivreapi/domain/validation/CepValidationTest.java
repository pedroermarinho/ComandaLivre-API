package io.github.pedroermarinho.comandalivreapi.domain.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.CepInvalidException;

public class CepValidationTest {

    @Test
    public void cepValidationReturnsNotThrow(){
        assertDoesNotThrow(()->CepValidation.validationThrow("60440134"));
    }

    @Test
    public void cepValidationReturnsThrowCepInvalidException(){
        assertThrows(CepInvalidException.class,()->CepValidation.validationThrow("60440-134"));
    }
    
    @Test
    public void valueNullCepValidationReturnsThrowCepInvalidException(){
        assertThrows(CepInvalidException.class,()->CepValidation.validationThrow(null));
    }

    @Test
    public void cepValidationReturnsTrue(){
        assertTrue(CepValidation.validation("60440134"));
    }

    @Test
    public void cepValidationReturnsFalse(){
        assertFalse(CepValidation.validation("6044-0134"));
    }

    @Test
    public void valeueNullCepValidationReturnsFalse(){
        assertFalse(CepValidation.validation(null));
    }
}
