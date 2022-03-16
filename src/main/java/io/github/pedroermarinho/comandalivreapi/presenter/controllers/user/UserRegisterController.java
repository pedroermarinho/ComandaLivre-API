package io.github.pedroermarinho.comandalivreapi.presenter.controllers.user;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.RegisterUser;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.UserPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.UserConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.UserForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + UserPathRest.USER_REGISTER)
@Tag(name = "Usuário", description = "Operações do usuário")
public class UserRegisterController {

    private final RegisterUser registerUser;
    private final UserConvert userConvert;

    public UserRegisterController(RegisterUser registerUser, UserConvert userConvert) {
        this.registerUser = registerUser;
        this.userConvert = userConvert;
    }

    @Operation(summary = "Cadastrar usuário", tags = {"Usuário"})
    @PostMapping
    public ResponseEntity<UserDTO> registerUser(UserForm userForm) {
        final UserDTO user = registerUser.execute(userConvert.convert(userForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.id())
                .toUri();

        return ResponseEntity.created(uri).body(user);
    }

}
