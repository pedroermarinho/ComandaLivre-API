package io.github.pedroermarinho.comandalivreapi.presenter.controllers.user;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.StatusUser;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.UserPathRest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + UserPathRest.USER_STATUS)
public class StatusUserController {

    private final StatusUser statusUser;

    public StatusUserController(StatusUser statusUser) {
        this.statusUser = statusUser;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> disableUser(@PathVariable UUID id) {
        final UserDTO user = statusUser.disableUser(id);
        return ResponseEntity.ok().body(user);
    }
}
