package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.OrganizationEntity;
import io.github.pedroermarinho.comandalivreapi.infra.forms.OrganizationForm;

import java.util.ArrayList;
import java.util.List;

public class OrganizationConvert implements Convert<OrganizationEntity, OrganizationDTO, OrganizationForm> {

    @Override
    public OrganizationDTO formEntity(OrganizationEntity entity) {
        final OrganizationDTO dto = new OrganizationDTO();
        dto.setId(entity.getId());
        dto.setCreatedById(entity.getCreatedById());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModifiedById(entity.getCreatedById());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public OrganizationEntity formDTO(OrganizationDTO dto) {
        final OrganizationEntity entity = new OrganizationEntity();
        entity.setId(dto.getId());
        entity.setCreatedById(dto.getCreatedById());
        entity.setCreationDate(dto.getCreationDate());
        entity.setModifiedById(dto.getCreatedById());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public OrganizationDTO fromForm(OrganizationForm form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrganizationDTO> formEntity(List<OrganizationEntity> entityList) {
        final List<OrganizationDTO> dtos = new ArrayList<>();
        return dtos;
    }

}
