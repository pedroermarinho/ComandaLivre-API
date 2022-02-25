package io.github.pedroermarinho.comandalivreapi.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizations")
@DynamicUpdate
@DynamicInsert
@Data
@EqualsAndHashCode(callSuper = true)
public class OrganizationEntity extends Auditable {

    private String name;
    private String telefone;

    @OneToOne
    private AddressEntity addressEntity;

    @OneToMany(mappedBy = "organizationEntity")
    private List<EmployeeAtOrganizationEntity> employeeAtOrganizations = new ArrayList<>();

    @OneToMany(mappedBy = "organizationEntity")
    private List<ProductEntity> productEntities = new ArrayList<>();

    public OrganizationEntity(String name, String telefone, AddressEntity addressEntity) {
        this.name = name;
        this.telefone = telefone;
        this.addressEntity = addressEntity;
    }

    public OrganizationEntity() {
    }


}
