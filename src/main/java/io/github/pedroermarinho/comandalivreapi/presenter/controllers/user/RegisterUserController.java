package io.github.pedroermarinho.comandalivreapi.presenter.controllers.user;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.RegisterUser;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.UserPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.infra.forms.UserForm;
import io.github.pedroermarinho.comandalivreapi.infra.mappers.UserMapper;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + UserPathRest.USER_REGISTER)
public class RegisterUserController {

    private final RegisterUser registerUser;

    public RegisterUserController(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @PostMapping
    public ResponseEntity<UserDTO> registerUser(UserForm userForm) {
        final UserEntity user = registerUser.execute(userForm.converte());
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(UserMapper.INSTANCE.entityToResponse(user));
    }

}
