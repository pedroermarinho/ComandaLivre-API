package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pedroermarinho.comandalivreapi.domain.entities.EmployeeAtOrganizationEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public record EmployeeAtOrganizationDTO(
        UUID id,

        UUID createdById,

        UUID modifiedById,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date creationDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date lastModifiedDate,

        Boolean status,

        OrganizationDTO organization,

        EmployeeDTO employee,

        RoleDTO role
        ) implements Serializable {
        public EmployeeAtOrganizationDTO(OrganizationDTO organization,EmployeeDTO employee,RoleDTO role) {
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

        public EmployeeAtOrganizationDTO(EmployeeAtOrganizationEntity entity) {
                this(
                        entity.getId(),
                        entity.getCreatedById(),
                        entity.getModifiedById(),
                        entity.getCreationDate(),
                        entity.getLastModifiedDate(),
                        entity.getStatus(),
                        new OrganizationDTO(entity.getOrganizationEntity()),
                        new EmployeeDTO(entity.getEmployeeEntity()),
                        new RoleDTO(entity.getRoleEntity())
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
