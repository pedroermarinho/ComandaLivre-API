package io.github.pedroermarinho.comandalivreapi.infra.forms;

import io.github.pedroermarinho.comandalivreapi.domain.entities.User;

public class UserForm {

    public User converte(){
        final User user = new User();
        return user;
    }
}
