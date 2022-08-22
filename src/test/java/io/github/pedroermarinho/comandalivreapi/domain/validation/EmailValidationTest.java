package io.github.pedroermarinho.comandalivreapi.domain.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.UsernameInvalidException;

class EmailValidationTest {

    
    @Test
    void emailValidationReturnsNotThrow(){
        assertDoesNotThrow(()->new EmailValidation().validationThrow("exemplo@exemplo.com"));
    }

    @Test
    void emailValidationReturnsThrowEmailInvalidException(){
        assertThrows(UsernameInvalidException.class,()->new EmailValidation().validationThrow("exemplo@exemplo"));
    }
    
    @Test
    void emailValidationReturnsTrue(){
        assertTrue(new EmailValidation().validation("exemplo@exemplo.com"));
    }

    @Test
    void emailValidationReturnsFalse(){
        assertFalse(new EmailValidation().validation("exemplo@exemplo"));
    }
}
