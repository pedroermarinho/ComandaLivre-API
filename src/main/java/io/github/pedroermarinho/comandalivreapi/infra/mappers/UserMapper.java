package io.github.pedroermarinho.comandalivreapi.infra.mappers;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.github.pedroermarinho.comandalivreapi.domain.entities.User;
import io.github.pedroermarinho.comandalivreapi.infra.dtos.UserDTO;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @InheritInverseConfiguration
    UserDTO entityToResponse(User user);

    List<UserDTO> entityToResponse(List<User>  users);

    User entityToResponse(UserDTO userDTO);
}
