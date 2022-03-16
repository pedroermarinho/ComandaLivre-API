package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pedroermarinho.comandalivreapi.domain.entities.RoleEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public record RoleDTO(
        UUID id,

        UUID createdById,

        UUID modifiedById,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date creationDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date lastModifiedDate,

        Boolean status,

        String name,

        String description
) implements Serializable {

    public RoleDTO(String name, String description) {
        this(
                null,
                null,
                null,
                null,
                null,
                true,
                name,
                description
        );
    }

    public RoleDTO(RoleEntity entity) {
        this(
                entity.getId(),
                entity.getCreatedById(),
                entity.getModifiedById(),
                entity.getCreationDate(),
                entity.getLastModifiedDate(),
                entity.getStatus(),
                entity.getName(),
                entity.getDescription()
        );
    }

    public RoleEntity toEntity() {
        final RoleEntity entity = new RoleEntity();
        entity.setId(this.id);
        entity.setCreatedById(this.createdById);
        entity.setCreationDate(this.creationDate);
        entity.setModifiedById(this.modifiedById);
        entity.setLastModifiedDate(this.lastModifiedDate);
        entity.setStatus(this.status);
        entity.setName(this.name);
        entity.setDescription(this.description);
        return entity;
    }

}
