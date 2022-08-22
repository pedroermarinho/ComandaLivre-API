package io.github.pedroermarinho.comandalivreapi.domain.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pedroermarinho.comandalivreapi.domain.entities.ProductOfCommandEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


public record ProductOfCommandRecord(
        UUID id,

        UUID createdById,

        UUID modifiedById,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date creationDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date lastModifiedDate,

        Boolean status,

        Integer amount,

        CommandRecord command,

        ProductRecord product
) implements Serializable {

    public ProductOfCommandRecord(Integer amount, CommandRecord command, ProductRecord product) {
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

    public ProductOfCommandRecord(ProductOfCommandEntity entity) {
        this(
                entity.getId(),
                entity.getCreatedById(),
                entity.getModifiedById(),
                entity.getCreationDate(),
                entity.getLastModifiedDate(),
                entity.getStatus(),
                entity.getAmount(),
                new CommandRecord(entity.getCommandEntity()),
                new ProductRecord(entity.getProductEntity())
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
