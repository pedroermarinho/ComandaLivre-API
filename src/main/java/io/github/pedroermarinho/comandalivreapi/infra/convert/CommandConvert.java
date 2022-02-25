package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.CommandEntity;
import io.github.pedroermarinho.comandalivreapi.infra.forms.CommandForm;

import java.util.ArrayList;
import java.util.List;

public class CommandConvert implements Convert<CommandEntity, CommandDTO, CommandForm> {

    @Override
    public CommandDTO formEntity(CommandEntity entity) {
        final CommandDTO dto = new CommandDTO();
        dto.setId(entity.getId());
        dto.setCreatedById(entity.getCreatedById());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModifiedById(entity.getCreatedById());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public CommandEntity formDTO(CommandDTO dto) {
        final CommandEntity entity = new CommandEntity();
        entity.setId(dto.getId());
        entity.setCreatedById(dto.getCreatedById());
        entity.setCreationDate(dto.getCreationDate());
        entity.setModifiedById(dto.getCreatedById());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public CommandDTO fromForm(CommandForm form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CommandDTO> formEntity(List<CommandEntity> entityList) {
        final List<CommandDTO> dtos = new ArrayList<>();
        return dtos;
    }

}
