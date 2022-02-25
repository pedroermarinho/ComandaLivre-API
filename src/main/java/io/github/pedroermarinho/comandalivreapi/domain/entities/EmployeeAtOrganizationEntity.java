package io.github.pedroermarinho.comandalivreapi.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_at_organization")
@DynamicUpdate
@DynamicInsert
@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeAtOrganizationEntity extends Auditable {

    @ManyToOne
    private OrganizationEntity organizationEntity;

    @ManyToOne
    private EmployeeEntity employeeEntity;

    @ManyToOne
    private RoleEntity roleEntity;

    public EmployeeAtOrganizationEntity() {
    }

    public EmployeeAtOrganizationEntity(OrganizationEntity organizationEntity, EmployeeEntity employeeEntity,
                                        RoleEntity roleEntity) {
        this.organizationEntity = organizationEntity;
        this.employeeEntity = employeeEntity;
        this.roleEntity = roleEntity;
    }


}
