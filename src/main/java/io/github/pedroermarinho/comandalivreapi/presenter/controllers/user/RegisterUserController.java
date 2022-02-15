package io.github.pedroermarinho.comandalivreapi.presenter.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pedroermarinho.comandalivreapi.domain.entities.User;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.RegisterUser;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.UserPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.infra.forms.UserForm;
import io.github.pedroermarinho.comandalivreapi.infra.mappers.UserMapper;

@RestController
@RequestMapping(value = PathRest.API+PathRest.VERSION+UserPathRest.USER_REGISTER)
public class RegisterUserController {
    
    private final RegisterUser registerUser;

    public RegisterUserController(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserDTO> registerUser(UserForm userForm){
        final User user = registerUser.execute(userForm.converte());
        return ResponseEntity.ok().body(UserMapper.INSTANCE.entityToResponse(user));
    }

}
