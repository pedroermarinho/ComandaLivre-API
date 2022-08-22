package io.github.pedroermarinho.comandalivreapi.domain.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pedroermarinho.comandalivreapi.domain.entities.OrganizationEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public record OrganizationRecord(
        UUID id,

        UUID createdById,

        UUID modifiedById,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date creationDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date lastModifiedDate,

        Boolean status,

        String name,

        String telefone,

        AddressRecord address
) implements Serializable {

    public OrganizationRecord(String name, String telefone, AddressRecord address) {
        this(
                null,
                null,
                null,
                null,
                null,
                true,
                name,
                telefone,
                address
        );
    }

    public OrganizationRecord(OrganizationEntity entity) {
        this(
                entity.getId(),
                entity.getCreatedById(),
                entity.getModifiedById(),
                entity.getCreationDate(),
                entity.getLastModifiedDate(),
                entity.getStatus(),
                entity.getName(),
                entity.getTelefone(),
                new AddressRecord(entity.getAddressEntity())
        );
    }

    public OrganizationEntity toEntity() {
        final OrganizationEntity entity = new OrganizationEntity();
        entity.setId(this.id);
        entity.setCreatedById(this.createdById);
        entity.setCreationDate(this.creationDate);
        entity.setModifiedById(this.modifiedById);
        entity.setLastModifiedDate(this.lastModifiedDate);
        entity.setStatus(this.status);
        entity.setName(this.name);
        entity.setTelefone(this.name);
        entity.setAddressEntity(this.address.toEntity());
        return entity;
    }

}
