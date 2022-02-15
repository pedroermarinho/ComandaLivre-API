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


import io.github.pedroermarinho.comandalivreapi.domain.entities.User;
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
    private void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

        userRepository = new UserRepositoryImpl(userDataSource);

        registerUser = new RegisterUser(userRepository);
    }

    @Test
    void registerUserReturnsUser() {
        
        when(userDataSource.save(any(User.class))).thenReturn(new User());
        when(userDataSource.findById(any(UUID.class))).thenReturn(Optional.of(new User()));

        final User user = new User();

        user.setEmail("exemplo@exemplo.com");
        user.setName("exemplo");
        user.setUsername("exemplo");
        user.setPassword("exemplo");

        assertInstanceOf(User.class, registerUser.execute(user));
    }

    @Test
    void registerUserReturnsThrowsEmailInvalidException() {
        
        when(userDataSource.save(any(User.class))).thenReturn(new User());
        final User user = new User();

        user.setEmail("exemplo@exemplo");
        user.setName("exemplo");
        user.setUsername("exemplo");
        user.setPassword("exemplo");

        assertThrows(UsernameInvalidException.class,()-> registerUser.execute(user));
    }
}
