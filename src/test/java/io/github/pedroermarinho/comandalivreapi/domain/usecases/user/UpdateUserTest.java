package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.UsernameInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.pedroermarinho.comandalivreapi.domain.record.UserRecord;
import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.UserDataSource;
import io.github.pedroermarinho.comandalivreapi.infra.repositories.UserRepositoryImpl;


class UpdateUserTest {
    @Mock
    private UserDataSource userDataSource;

    private UserRepository userRepository;

    private UpdateUser updateUser;


    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

        userRepository = new UserRepositoryImpl(userDataSource);

        updateUser = new UpdateUser(userRepository);
    }
//
//    @Test
//    void updateUserReturnsUser() {
//
//        when(userDataSource.save(any(UserEntity.class))).thenReturn(new UserEntity());
//        when(userDataSource.findById(any(UUID.class))).thenReturn(Optional.of(new UserEntity()));
//
//        final UserRecord user = new UserRecord("exemplo@exemplo.com","exemplo","exemplo","exemplo","exemplo");
//
//        assertInstanceOf(UserRecord.class, updateUser.execute(UUID.randomUUID(),user));
//    }
//
//    @Test
//    void updateUserReturnsThrowsObjectNotFoundException() {
//
//        when(userDataSource.save(any(UserEntity.class))).thenReturn(new UserEntity());
//        final UserRecord user = new UserRecord("exemplo@exemplo.com","exemplo","exemplo","exemplo","exemplo");
//
//        assertThrows(UsernameInvalidException.class,()-> updateUser.execute(UUID.randomUUID(),user));
//    }
}
