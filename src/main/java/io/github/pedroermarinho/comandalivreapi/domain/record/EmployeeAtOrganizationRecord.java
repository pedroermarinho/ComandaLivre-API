package io.github.pedroermarinho.comandalivreapi.domain.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pedroermarinho.comandalivreapi.domain.entities.EmployeeAtOrganizationEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public record EmployeeAtOrganizationRecord(
        UUID id,

        UUID createdById,

        UUID modifiedById,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date creationDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date lastModifiedDate,

        Boolean status,

        OrganizationRecord organization,

        EmployeeRecord employee,

        RoleRecord role
) implements Serializable {
    public EmployeeAtOrganizationRecord(OrganizationRecord organization, EmployeeRecord employee, RoleRecord role) {
        this(
                null,
                null,
                null,
                null,
                null,
                true,
                organization,
                employee,
                role
        );
    }

    public EmployeeAtOrganizationRecord(EmployeeAtOrganizationEntity entity) {
        this(
                entity.getId(),
                entity.getCreatedById(),
                entity.getModifiedById(),
                entity.getCreationDate(),
                entity.getLastModifiedDate(),
                entity.getStatus(),
                new OrganizationRecord(entity.getOrganizationEntity()),
                new EmployeeRecord(entity.getEmployeeEntity()),
                new RoleRecord(entity.getRoleEntity())
        );
    }

    public EmployeeAtOrganizationEntity toEntity() {
        final EmployeeAtOrganizationEntity entity = new EmployeeAtOrganizationEntity();
        entity.setId(this.id);
        entity.setCreatedById(this.createdById);
        entity.setCreationDate(this.creationDate);
        entity.setModifiedById(this.modifiedById);
        entity.setLastModifiedDate(this.lastModifiedDate);
        entity.setStatus(this.status);
        entity.setOrganizationEntity(this.organization.toEntity());
        entity.setEmployeeEntity(this.employee.toEntity());
        entity.setRoleEntity(this.role.toEntity());
        return entity;
    }

}
