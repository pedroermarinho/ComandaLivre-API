package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeAtOrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.EmployeeAtOrganizationEntity;
import io.github.pedroermarinho.comandalivreapi.infra.forms.EmployeeAtOrganizationForm;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAtOrganizationConvert implements Convert<EmployeeAtOrganizationEntity, EmployeeAtOrganizationDTO, EmployeeAtOrganizationForm> {

    @Override
    public EmployeeAtOrganizationDTO formEntity(EmployeeAtOrganizationEntity entity) {
        final EmployeeAtOrganizationDTO dto = new EmployeeAtOrganizationDTO();
        dto.setId(entity.getId());
        dto.setCreatedById(entity.getCreatedById());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModifiedById(entity.getCreatedById());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public EmployeeAtOrganizationEntity formDTO(EmployeeAtOrganizationDTO dto) {
        final EmployeeAtOrganizationEntity entity = new EmployeeAtOrganizationEntity();
        entity.setId(dto.getId());
        entity.setCreatedById(dto.getCreatedById());
        entity.setCreationDate(dto.getCreationDate());
        entity.setModifiedById(dto.getCreatedById());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public EmployeeAtOrganizationDTO fromForm(EmployeeAtOrganizationForm form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EmployeeAtOrganizationDTO> formEntity(List<EmployeeAtOrganizationEntity> entityList) {
        final List<EmployeeAtOrganizationDTO> dtos = new ArrayList<>();
        return dtos;
    }

}
