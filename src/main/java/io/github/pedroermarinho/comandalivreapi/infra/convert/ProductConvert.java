package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.ProductEntity;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductForm;

import java.util.ArrayList;
import java.util.List;

public class ProductConvert implements Convert<ProductEntity, ProductDTO, ProductForm> {

    @Override
    public ProductDTO formEntity(ProductEntity entity) {
        final ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setCreatedById(entity.getCreatedById());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModifiedById(entity.getCreatedById());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public ProductEntity formDTO(ProductDTO dto) {
        final ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setCreatedById(dto.getCreatedById());
        entity.setCreationDate(dto.getCreationDate());
        entity.setModifiedById(dto.getCreatedById());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public ProductDTO fromForm(ProductForm form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProductDTO> formEntity(List<ProductEntity> entityList) {
        final List<ProductDTO> dtos = new ArrayList<>();
        return dtos;
    }

}
