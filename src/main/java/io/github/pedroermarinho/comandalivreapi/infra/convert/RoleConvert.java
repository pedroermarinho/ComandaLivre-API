package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.RoleDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.RoleEntity;
import io.github.pedroermarinho.comandalivreapi.infra.forms.RoleForm;

import java.util.ArrayList;
import java.util.List;

public class RoleConvert implements Convert<RoleEntity, RoleDTO, RoleForm> {

    @Override
    public RoleDTO formEntity(RoleEntity entity) {
        final RoleDTO dto = new RoleDTO();
        dto.setId(entity.getId());
        dto.setCreatedById(entity.getCreatedById());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModifiedById(entity.getCreatedById());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public RoleEntity formDTO(RoleDTO dto) {
        final RoleEntity entity = new RoleEntity();
        entity.setId(dto.getId());
        entity.setCreatedById(dto.getCreatedById());
        entity.setCreationDate(dto.getCreationDate());
        entity.setModifiedById(dto.getCreatedById());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public RoleDTO fromForm(RoleForm form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RoleDTO> formEntity(List<RoleEntity> entityList) {
        final List<RoleDTO> dtos = new ArrayList<>();
        return dtos;
    }

}
