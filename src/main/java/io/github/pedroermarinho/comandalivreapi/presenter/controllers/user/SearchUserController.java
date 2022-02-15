package io.github.pedroermarinho.comandalivreapi.presenter.controllers.user;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pedroermarinho.comandalivreapi.domain.entities.User;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.SearchUser;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.UserPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.infra.mappers.UserMapper;

@RestController
@RequestMapping(value = PathRest.API+PathRest.VERSION+UserPathRest.USER_SEARCH)
public class SearchUserController {

    private final SearchUser searchUser;

    public SearchUserController(SearchUser searchUser) {
        this.searchUser = searchUser;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> searchUserAll() {
        final List<User> users = searchUser.searchUserAll();
        return ResponseEntity.ok().body(UserMapper.INSTANCE.entityToResponse(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> searchUserById(@PathVariable UUID id) {
        final User user = searchUser.searchUserById(id); 
        return ResponseEntity.ok().body(UserMapper.INSTANCE.entityToResponse(user));
    }

    @GetMapping(UserPathRest.USER_SEARCH_EMAIL)
    public ResponseEntity<UserDTO> searchUserByEmail(@PathVariable String email) {
        final User user = searchUser.searchUserByEmail(email); 
        return ResponseEntity.ok().body(UserMapper.INSTANCE.entityToResponse(user));
    }

    @GetMapping(UserPathRest.USER_SEARCH_USERNAME)
    public ResponseEntity<UserDTO> searchUserByUsername(@PathVariable String username) {
        final User user = searchUser.searchUserByUsername(username); 
        return ResponseEntity.ok().body(UserMapper.INSTANCE.entityToResponse(user));
    }
    
}
