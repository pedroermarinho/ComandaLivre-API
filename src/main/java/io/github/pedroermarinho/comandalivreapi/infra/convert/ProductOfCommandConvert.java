package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.ProductOfCommandEntity;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductOfCommandForm;

import java.util.ArrayList;
import java.util.List;

public class ProductOfCommandConvert implements Convert<ProductOfCommandEntity, ProductOfCommandDTO, ProductOfCommandForm> {

    @Override
    public ProductOfCommandDTO formEntity(ProductOfCommandEntity entity) {
        final ProductOfCommandDTO dto = new ProductOfCommandDTO();
        dto.setId(entity.getId());
        dto.setCreatedById(entity.getCreatedById());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModifiedById(entity.getCreatedById());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public ProductOfCommandEntity formDTO(ProductOfCommandDTO dto) {
        final ProductOfCommandEntity entity = new ProductOfCommandEntity();
        entity.setId(dto.getId());
        entity.setCreatedById(dto.getCreatedById());
        entity.setCreationDate(dto.getCreationDate());
        entity.setModifiedById(dto.getCreatedById());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public ProductOfCommandDTO fromForm(ProductOfCommandForm form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProductOfCommandDTO> formEntity(List<ProductOfCommandEntity> entityList) {
        final List<ProductOfCommandDTO> dtos = new ArrayList<>();
        return dtos;
    }

}
