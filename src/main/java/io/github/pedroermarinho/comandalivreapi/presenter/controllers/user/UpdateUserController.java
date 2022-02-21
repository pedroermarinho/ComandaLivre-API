package io.github.pedroermarinho.comandalivreapi.presenter.controllers.user;

import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.UpdateUser;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.UserPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.infra.forms.UserForm;
import io.github.pedroermarinho.comandalivreapi.infra.mappers.UserMapper;

@RestController
@RequestMapping(value = PathRest.API+PathRest.VERSION+UserPathRest.USER_UPDATE)
public class UpdateUserController {
    
    private final UpdateUser updateUser;


    public UpdateUserController(UpdateUser updateUser) {
        this.updateUser = updateUser;
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> disableUser(@PathVariable UUID id,@Valid @RequestBody UserForm user){
        final UserEntity userEntity = updateUser.execute(id,user.converte());
        return ResponseEntity.ok().body(UserMapper.INSTANCE.entityToResponse(userEntity));
    }
}
