package io.github.pedroermarinho.comandalivreapi.domain.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.UsernameInvalidException;

public class EmailValidationTest {

    
    @Test
    public void emailValidationReturnsNotThrow(){
        assertDoesNotThrow(()->EmailValidation.validationThrow("exemplo@exemplo.com"));
    }

    @Test
    public void emailValidationReturnsThrowEmailInvalidException(){
        assertThrows(UsernameInvalidException.class,()->EmailValidation.validationThrow("exemplo@exemplo"));
    }
    
    @Test
    public void valueNullEmailValidationReturnsThrowEmailInvalidException(){
        assertThrows(UsernameInvalidException.class,()->EmailValidation.validationThrow(null));
    }

    @Test
    public void emailValidationReturnsTrue(){
        assertTrue(EmailValidation.validation("exemplo@exemplo.com"));
    }

    @Test
    public void emailValidationReturnsFalse(){
        assertFalse(EmailValidation.validation("exemplo@exemplo"));
    }

    @Test
    public void valeueNullEmailValidationReturnsFalse(){
        assertFalse(EmailValidation.validation(null));
    }
}
