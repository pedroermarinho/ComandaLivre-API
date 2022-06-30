package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotNullException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.UsernameInvalidException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.UserDataSource;
import io.github.pedroermarinho.comandalivreapi.infra.repositories.UserRepositoryImpl;

public class SearchUserTest {

    @Mock
    private UserDataSource userDataSource;

    private UserRepository userRepository;

    private SearchUser searchUser;


    @BeforeEach
    private void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

        userRepository = new UserRepositoryImpl(userDataSource);

        searchUser = new SearchUser(userRepository);
    }

    @Test
    void searchUserByIdReturnsUser() {
        
        when(userDataSource.findById(any(UUID.class))).thenReturn(Optional.of(new UserEntity()));

        assertInstanceOf(UserDTO.class, searchUser.searchUserById(UUID.randomUUID()));
    }
    
    @Test
    void searchUserByIdReturnsThrowIllegalArgumentException() {
        
        when(userDataSource.findById(any(UUID.class))).thenReturn(Optional.of(new UserEntity()));

        assertThrows(NotNullException.class,()-> searchUser.searchUserById(null));
    }

    @Test
    void searchUserByEmailReturnsUser() {
        
        when(userDataSource.findByEmail(any(String.class))).thenReturn(Optional.of(new UserEntity()));

        assertInstanceOf(UserDTO.class, searchUser.searchUserByEmail("exemplo@exemplo.com"));
    }

    @Test
    void searchUserByEmailReturnsThrowEmailInvalidException() {
        
        when(userDataSource.findByEmail(any(String.class))).thenReturn(Optional.of(new UserEntity()));

        assertThrows(UsernameInvalidException.class,()-> searchUser.searchUserByEmail("exemplo@exemplo"));
    }

    @Test
    void searchUserByUsernameReturnsUser() {
        
        when(userDataSource.findByUsername(any(String.class))).thenReturn(Optional.of(new UserEntity()));

        assertInstanceOf(UserDTO.class, searchUser.searchUserByUsername("exemplo"));
    }

    @Test
    void searchUserByUsernameReturnsThrowUsernameInvalidException() {
        
        when(userDataSource.findByUsername(any(String.class))).thenReturn(Optional.of(new UserEntity()));

        assertThrows(UsernameInvalidException.class,()-> searchUser.searchUserByUsername("exemplo@exemplo.com"));
    }

    @Test
    void searchUserAllReturnsListUser() {
        
        when(userDataSource.findAll()).thenReturn( new ArrayList<>());

        assertInstanceOf(List.class, searchUser.searchUserAll());
    }
}
