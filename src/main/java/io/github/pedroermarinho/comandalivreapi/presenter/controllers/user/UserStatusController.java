package io.github.pedroermarinho.comandalivreapi.presenter.controllers.user;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.StatusUser;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.UserPathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + UserPathRest.USER_STATUS)
@Tag(name = "Usuário", description = "Operações do usuário")
public class UserStatusController {

    private final StatusUser statusUser;

    public UserStatusController(StatusUser statusUser) {
        this.statusUser = statusUser;
    }

    @Operation(summary = "Desativar usuário", tags = {"Usuário"})
    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> disableUser(@PathVariable UUID id) {
        final UserDTO user = statusUser.disableUser(id);
        return ResponseEntity.ok().body(user);
    }
}
