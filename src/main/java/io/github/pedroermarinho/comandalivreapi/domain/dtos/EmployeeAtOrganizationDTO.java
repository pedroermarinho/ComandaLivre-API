package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeAtOrganizationDTO extends AuditableDTO {

    private OrganizationDTO organization;

    private EmployeeDTO employee;

    private RoleDTO role;

}
