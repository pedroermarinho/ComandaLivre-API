package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pedroermarinho.comandalivreapi.domain.entities.CommandEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public record CommandDTO(
        UUID id,

        UUID createdById,

        UUID modifiedById,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date creationDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date lastModifiedDate,

        Boolean status,

        Boolean paidOff,

        String identification
) implements Serializable {

    public CommandDTO(Boolean paidOff, String identification) {
        this(
                null,
                null,
                null,
                null,
                null,
                true,
                paidOff,
                identification
                );
    }

    public CommandDTO(CommandEntity entity) {
        this(
                entity.getId(),
                entity.getCreatedById(),
                entity.getModifiedById(),
                entity.getCreationDate(),
                entity.getLastModifiedDate(),
                entity.getStatus(),
                entity.getPaidOff(),
                entity.getIdentification()
        );
    }

    public CommandEntity toEntity() {
        final CommandEntity entity = new CommandEntity();
        entity.setId(this.id);
        entity.setCreatedById(this.createdById);
        entity.setCreationDate(this.creationDate);
        entity.setModifiedById(this.modifiedById);
        entity.setLastModifiedDate(this.lastModifiedDate);
        entity.setStatus(this.status);
        entity.setPaidOff(this.paidOff);
        entity.setIdentification(this.identification);
        return entity;
    }

}
