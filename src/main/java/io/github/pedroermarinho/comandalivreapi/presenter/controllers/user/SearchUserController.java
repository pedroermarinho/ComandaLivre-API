package io.github.pedroermarinho.comandalivreapi.presenter.controllers.user;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.SearchUser;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.UserPathRest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + UserPathRest.USER_SEARCH)
public class SearchUserController {

    private final SearchUser searchUser;

    public SearchUserController(SearchUser searchUser) {
        this.searchUser = searchUser;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> searchUserAll() {
        final List<UserDTO> users = searchUser.searchUserAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> searchUserById(@PathVariable UUID id) {
        final UserDTO user = searchUser.searchUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(UserPathRest.USER_SEARCH_EMAIL)
    public ResponseEntity<UserDTO> searchUserByEmail(@PathVariable String email) {
        final UserDTO user = searchUser.searchUserByEmail(email);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(UserPathRest.USER_SEARCH_USERNAME)
    public ResponseEntity<UserDTO> searchUserByUsername(@PathVariable String username) {
        final UserDTO user = searchUser.searchUserByUsername(username);
        return ResponseEntity.ok().body(user);
    }

}
