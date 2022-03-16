package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pedroermarinho.comandalivreapi.domain.entities.ProductOfCommandEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


public record ProductOfCommandDTO(
        UUID id,

        UUID createdById,

        UUID modifiedById,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date creationDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date lastModifiedDate,

        Boolean status,

        Integer amount,

        CommandDTO command,

        ProductDTO product
) implements Serializable {

    public ProductOfCommandDTO(Integer amount, CommandDTO command, ProductDTO product) {
        this(
                null,
                null,
                null,
                null,
                null,
                true,
                amount,
                command,
                product
        );
    }

    public ProductOfCommandDTO(ProductOfCommandEntity entity) {
        this(
                entity.getId(),
                entity.getCreatedById(),
                entity.getModifiedById(),
                entity.getCreationDate(),
                entity.getLastModifiedDate(),
                entity.getStatus(),
                entity.getAmount(),
                new CommandDTO(entity.getCommandEntity()),
                new ProductDTO(entity.getProductEntity())
        );
    }

    public ProductOfCommandEntity toEntity() {
        final ProductOfCommandEntity entity = new ProductOfCommandEntity();
        entity.setId(this.id);
        entity.setCreatedById(this.createdById);
        entity.setCreationDate(this.creationDate);
        entity.setModifiedById(this.modifiedById);
        entity.setLastModifiedDate(this.lastModifiedDate);
        entity.setStatus(this.status);
        entity.setAmount(this.amount);
        entity.setCommandEntity(this.command.toEntity());
        entity.setProductEntity(this.product.toEntity());
        return entity;
    }
}
