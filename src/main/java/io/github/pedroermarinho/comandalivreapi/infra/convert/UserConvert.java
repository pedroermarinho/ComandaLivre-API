package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.infra.forms.UserForm;
import org.springframework.core.convert.converter.Converter;

public class UserConvert implements Converter<UserForm, UserDTO> {

    @Override
    public UserDTO convert(UserForm source) {
        return new UserDTO(
                source.getName(),
                source.getEmail(),
                source.getUsername(),
                source.getPassword(),
                source.getTelefone()
        );
    }
}
