package io.github.pedroermarinho.comandalivreapi.presenter.controllers.user;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pedroermarinho.comandalivreapi.domain.entities.User;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.StatusUser;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.UserPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.infra.mappers.UserMapper;

@RestController
@RequestMapping(value = PathRest.API+PathRest.VERSION+UserPathRest.USER_STATUS)
public class StatusUserController {

    private final StatusUser statusUser;

    public StatusUserController(StatusUser statusUser) {
        this.statusUser = statusUser;
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDTO> disableUser(@PathVariable UUID id){
        final User user= statusUser.disableUser(id);
        return ResponseEntity.ok().body(UserMapper.INSTANCE.entityToResponse(user));
    }
}
