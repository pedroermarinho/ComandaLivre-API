package io.github.pedroermarinho.comandalivreapi.domain.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pedroermarinho.comandalivreapi.domain.entities.ProductEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record ProductRecord(
        UUID id,

        UUID createdById,

        UUID modifiedById,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date creationDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date lastModifiedDate,

        Boolean status,

        String name,

        String description,

        BigDecimal price,

        OrganizationRecord organization
) implements Serializable {

    public ProductRecord(String name, String description, BigDecimal price, OrganizationRecord organization) {
        this(
                null,
                null,
                null,
                null,
                null,
                true,
                name,
                description,
                price,
                organization
        );
    }

    public ProductRecord(ProductEntity entity) {
        this(
                entity.getId(),
                entity.getCreatedById(),
                entity.getModifiedById(),
                entity.getCreationDate(),
                entity.getLastModifiedDate(),
                entity.getStatus(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                new OrganizationRecord(entity.getOrganizationEntity())
        );
    }

    public ProductEntity toEntity() {
        final ProductEntity entity = new ProductEntity();
        entity.setId(this.id);
        entity.setCreatedById(this.createdById);
        entity.setCreationDate(this.creationDate);
        entity.setModifiedById(this.modifiedById);
        entity.setLastModifiedDate(this.lastModifiedDate);
        entity.setStatus(this.status);
        entity.setName(this.name);
        entity.setDescription(this.description);
        entity.setPrice(this.price);
        entity.setOrganizationEntity(this.organization.toEntity());
        return entity;
    }
}
