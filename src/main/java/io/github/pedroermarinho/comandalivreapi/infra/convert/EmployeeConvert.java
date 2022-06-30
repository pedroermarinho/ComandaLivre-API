package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.SearchUser;
import io.github.pedroermarinho.comandalivreapi.infra.forms.EmployeeForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConvert implements Converter<EmployeeForm, EmployeeDTO> {

    private final SearchUser searchUser;

    public EmployeeConvert(SearchUser searchUser) {
        this.searchUser = searchUser;
    }

    @Override
    public EmployeeDTO convert(EmployeeForm source) {
        final UserDTO userDTO = searchUser.searchUserById(source.userId());
        return new EmployeeDTO(source.registration(), userDTO);
    }
}
