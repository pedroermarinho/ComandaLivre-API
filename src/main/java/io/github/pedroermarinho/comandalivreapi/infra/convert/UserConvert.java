package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.infra.forms.UserForm;

import java.util.ArrayList;
import java.util.List;

public class UserConvert implements Convert<UserEntity, UserDTO, UserForm> {

    @Override
    public UserDTO formEntity(UserEntity entity) {
        final UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setCreatedById(entity.getCreatedById());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModifiedById(entity.getCreatedById());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public UserEntity formDTO(UserDTO dto) {
        final UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setCreatedById(dto.getCreatedById());
        entity.setCreationDate(dto.getCreationDate());
        entity.setModifiedById(dto.getCreatedById());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public UserDTO fromForm(UserForm form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserDTO> formEntity(List<UserEntity> entityList) {
        final List<UserDTO> dtos = new ArrayList<>();
        return dtos;
    }

}
