package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.UsernameInvalidException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.UserDataSource;
import io.github.pedroermarinho.comandalivreapi.infra.repositories.UserRepositoryImpl;

public class RegisterUserTest {

    @Mock
    private UserDataSource userDataSource;

    private UserRepository userRepository;

    private RegisterUser registerUser;


    @BeforeEach
    private void setUp() {
        MockitoAnnotations.openMocks(this);

        userRepository = new UserRepositoryImpl(userDataSource);

        registerUser = new RegisterUser(userRepository);
    }

    @Test
    void registerUserReturnsUser() {
        
        when(userDataSource.save(any(UserEntity.class))).thenReturn(new UserEntity());
        when(userDataSource.findById(any(UUID.class))).thenReturn(Optional.of(new UserEntity()));

        final UserDTO user = new UserDTO("exemplo@exemplo.com","exemplo","exemplo","exemplo","exemplo");

        assertInstanceOf(UserDTO.class, registerUser.execute(user));
    }

    @Test
    void registerUserReturnsThrowsEmailInvalidException() {
        
        when(userDataSource.save(any(UserEntity.class))).thenReturn(new UserEntity());
        final UserDTO user = new UserDTO("exemplo@exemplo","exemplo","exemplo","exemplo","exemplo");


        assertThrows(UsernameInvalidException.class,()-> registerUser.execute(user));
    }
}
