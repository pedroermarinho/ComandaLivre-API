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

import io.github.pedroermarinho.comandalivreapi.domain.record.UserRecord;
import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.UserDataSource;
import io.github.pedroermarinho.comandalivreapi.infra.repositories.UserRepositoryImpl;

class StatusUserTest {

    @Mock
    private UserDataSource userDataSource;

    private UserRepository userRepository;

    private StatusUser statusUser;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

        userRepository = new UserRepositoryImpl(userDataSource);

        statusUser = new StatusUser(userRepository);
    }

    @Test
    void disableUserReturnsUser() {
        
        when(userDataSource.save(any(UserEntity.class))).thenReturn(new UserEntity());
        when(userDataSource.findById(any(UUID.class))).thenReturn(Optional.of(new UserEntity()));

        assertInstanceOf(UserRecord.class, statusUser.disableUser(UUID.randomUUID()));
    }

    @Test
    void disableUserReturnsThrowsObjectNotFoundException() {
        
        when(userDataSource.save(any(UserEntity.class))).thenReturn(new UserEntity());

        assertThrows(ObjectNotFoundException.class,()-> statusUser.disableUser(UUID.randomUUID()));
    }

    @Test
    void enableUserReturnsUser() {
        
        when(userDataSource.save(any(UserEntity.class))).thenReturn(new UserEntity());
        when(userDataSource.findById(any(UUID.class))).thenReturn(Optional.of(new UserEntity()));

        assertInstanceOf(UserRecord.class, statusUser.enableUser(UUID.randomUUID()));
    }

    @Test
    void enableUserReturnsThrowsObjectNotFoundException() {
        
        when(userDataSource.save(any(UserEntity.class))).thenReturn(new UserEntity());

        assertThrows(ObjectNotFoundException.class,()-> statusUser.enableUser(UUID.randomUUID()));
    }


}
