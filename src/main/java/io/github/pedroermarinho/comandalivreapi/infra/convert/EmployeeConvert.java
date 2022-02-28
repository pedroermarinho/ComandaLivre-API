package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.EmployeeEntity;
import io.github.pedroermarinho.comandalivreapi.infra.forms.EmployeeForm;

import java.util.ArrayList;
import java.util.List;

public class EmployeeConvert implements Convert<EmployeeEntity, EmployeeDTO, EmployeeForm> {

    @Override
    public EmployeeDTO formEntity(EmployeeEntity entity) {
        final EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setCreatedById(entity.getCreatedById());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModifiedById(entity.getCreatedById());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public EmployeeEntity formDTO(EmployeeDTO dto) {
        final EmployeeEntity entity = new EmployeeEntity();
        entity.setId(dto.getId());
        entity.setCreatedById(dto.getCreatedById());
        entity.setCreationDate(dto.getCreationDate());
        entity.setModifiedById(dto.getCreatedById());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public EmployeeDTO fromForm(EmployeeForm form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EmployeeDTO> formEntity(List<EmployeeEntity> entityList) {
        final List<EmployeeDTO> dtos = new ArrayList<>();
        return dtos;
    }

}
