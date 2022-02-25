package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.AddressEntity;
import io.github.pedroermarinho.comandalivreapi.infra.forms.AddressForm;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressConvert implements Convert<AddressEntity, AddressDTO, AddressForm> {

    @Override
    public AddressDTO formEntity(AddressEntity entity) {
        final AddressDTO dto = new AddressDTO();
        dto.setId(entity.getId());
        dto.setCreatedById(entity.getCreatedById());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModifiedById(entity.getCreatedById());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public AddressEntity formDTO(AddressDTO dto) {
        final AddressEntity entity = new AddressEntity();
        entity.setId(dto.getId());
        entity.setCreatedById(dto.getCreatedById());
        entity.setCreationDate(dto.getCreationDate());
        entity.setModifiedById(dto.getCreatedById());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public AddressDTO fromForm(AddressForm form) {
        final AddressDTO dto = new AddressDTO();
        return dto;
    }

    @Override
    public List<AddressDTO> formEntity(List<AddressEntity> entityList) {
        final List<AddressDTO> dtos = new ArrayList<>();
        return dtos;
    }

}
