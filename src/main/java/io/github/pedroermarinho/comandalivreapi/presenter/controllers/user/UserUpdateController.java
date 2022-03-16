package io.github.pedroermarinho.comandalivreapi.presenter.controllers.user;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.UpdateUser;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.UserPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.UserConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.UserForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + UserPathRest.USER_UPDATE)
@Tag(name = "Usuário", description = "Operações do usuário")
public class UserUpdateController {

    private final UpdateUser updateUser;
    private final UserConvert userConvert;

    public UserUpdateController(UpdateUser updateUser, UserConvert userConvert) {
        this.updateUser = updateUser;
        this.userConvert = userConvert;
    }

    @Operation(summary = "Atualizar usuário", tags = {"Usuário"})
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @Valid @RequestBody UserForm userForm) {
        final UserDTO user = updateUser.execute(id, userConvert.convert(userForm));
        return ResponseEntity.ok().body(user);
    }
}
