package io.github.pedroermarinho.comandalivreapi.infra.seed.data;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.RegisterUser;

import java.util.List;

public class UserData implements DataSeed {

    private final RegisterUser registerUser;

    public UserData(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @Override
    public void create() {
        final List<UserDTO> userList = List.of(
                new UserDTO("exemplo1", "exemplo1@exemplo.com", "exemplo1", "123", "12345676783"),
                new UserDTO("exemplo2", "exemplo2@exemplo.com", "exemplo2", "123", "12345676789"),
                new UserDTO("exemplo3", "exemplo3@exemplo.com", "exemplo3", "123", "12345676782")
        );

        userList.forEach(registerUser::execute);
    }
}
