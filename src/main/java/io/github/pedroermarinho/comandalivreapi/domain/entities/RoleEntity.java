package io.github.pedroermarinho.comandalivreapi.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@DynamicUpdate
@DynamicInsert
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends Auditable {

    private String name;
    private String description;

    @OneToMany(mappedBy = "roleEntity")
    private List<EmployeeAtOrganizationEntity> employeeAtOrganizations = new ArrayList<>();

    public RoleEntity() {
    }

    public RoleEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }


}
