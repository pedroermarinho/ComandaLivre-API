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
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.UserDataSource;
import io.github.pedroermarinho.comandalivreapi.infra.repositories.UserRepositoryImpl;


public class UpdateUserTest {
    @Mock
    private UserDataSource userDataSource;

    private UserRepository userRepository;

    private UpdateUser updateUser;


    @BeforeEach
    private void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

        userRepository = new UserRepositoryImpl(userDataSource);

        updateUser = new UpdateUser(userRepository);
    }

    @Test
    void updateUserReturnsUser() {
        
        when(userDataSource.save(any(User.class))).thenReturn(new User());
        when(userDataSource.findById(any(UUID.class))).thenReturn(Optional.of(new User()));

        final User user = new User();

        user.setEmail("exemplo@exemplo.com");
        user.setName("exemplo");
        user.setUsername("exemplo");
        user.setPassword("exemplo");

        assertInstanceOf(User.class, updateUser.execute(UUID.randomUUID(),user));
    }

    @Test
    void updateUserReturnsThrowsObjectNotFoundException() {
        
        when(userDataSource.save(any(User.class))).thenReturn(new User());
        final User user = new User();

        user.setEmail("exemplo@exemplo.com");
        user.setName("exemplo");
        user.setUsername("exemplo");
        user.setPassword("exemplo");

        assertThrows(ObjectNotFoundException.class,()-> updateUser.execute(UUID.randomUUID(),user));
    }
}
