package io.github.pedroermarinho.comandalivreapi.presenter.controllers.user;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.UpdateUser;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.UserPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.UserConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.UserForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + UserPathRest.USER_UPDATE)
public class UpdateUserController {

    private final UpdateUser updateUser;
    private final UserConvert convert = new UserConvert();

    public UpdateUserController(UpdateUser updateUser) {
        this.updateUser = updateUser;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> disableUser(@PathVariable UUID id, @Valid @RequestBody UserForm userForm) {
        final UserDTO user = updateUser.execute(id, convert.fromForm(userForm));
        return ResponseEntity.ok().body(user);
    }
}
