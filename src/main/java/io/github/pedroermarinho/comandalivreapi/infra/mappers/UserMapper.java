package io.github.pedroermarinho.comandalivreapi.infra.mappers;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.infra.dtos.UserDTO;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @InheritInverseConfiguration
    UserDTO entityToResponse(UserEntity user);

    List<UserDTO> entityToResponse(List<UserEntity>  users);

    UserEntity entityToResponse(UserDTO userDTO);
}
