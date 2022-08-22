package io.github.pedroermarinho.comandalivreapi.domain.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pedroermarinho.comandalivreapi.domain.entities.EmployeeEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public record EmployeeRecord(
        UUID id,

        UUID createdById,

        UUID modifiedById,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date creationDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date lastModifiedDate,

        Boolean status,

        String registration,

        UserRecord user
) implements Serializable {
    public EmployeeRecord(String registration, UserRecord user) {
        this(
                null,
                null,
                null,
                null,
                null,
                true,
                registration,
                user
        );
    }

    public EmployeeRecord(EmployeeEntity entity) {
        this(
                entity.getId(),
                entity.getCreatedById(),
                entity.getModifiedById(),
                entity.getCreationDate(),
                entity.getLastModifiedDate(),
                entity.getStatus(),
                entity.getRegistration(),
                new UserRecord(entity.getUserEntity())
        );
    }

    public EmployeeEntity toEntity() {
        final EmployeeEntity entity = new EmployeeEntity();
        entity.setId(this.id);
        entity.setCreatedById(this.createdById);
        entity.setCreationDate(this.creationDate);
        entity.setModifiedById(this.modifiedById);
        entity.setLastModifiedDate(this.lastModifiedDate);
        entity.setStatus(this.status);
        entity.setRegistration(this.registration);
        entity.setUserEntity(this.user.toEntity());
        return entity;
    }


}
