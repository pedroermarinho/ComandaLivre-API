package io.github.pedroermarinho.comandalivreapi.infra.forms;

import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;

public class UserForm {

    public UserEntity converte(){
        final UserEntity user = new UserEntity();
        return user;
    }
}
