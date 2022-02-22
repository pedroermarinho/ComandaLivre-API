package io.github.pedroermarinho.comandalivreapi.domain.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Table(name = "roles")
@DynamicUpdate
@DynamicInsert
@Data
public class RoleEntity extends Auditable{
    
    private String name;
    private String description;

    @OneToMany(mappedBy = "roleEntity")
    private List<EmployeeAtOrganizationEntity> employeeAtOrganizations;

    public RoleEntity() {
    }

    public RoleEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    
}
