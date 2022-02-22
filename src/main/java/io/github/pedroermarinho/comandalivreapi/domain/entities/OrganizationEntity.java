package io.github.pedroermarinho.comandalivreapi.domain.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Table(name = "organizations")
@DynamicUpdate
@DynamicInsert
@Data
public class OrganizationEntity extends Auditable {

    private String name;
    private String telefone;

    @OneToOne
    private AddressEntity addressEntity;

    @OneToMany(mappedBy = "organizationEntity")
    private List<EmployeeAtOrganizationEntity> employeeAtOrganizations;

    @OneToMany(mappedBy = "organizationEntity")
    private List<ProductEntity> productEntities;
    
    public OrganizationEntity(String name, String telefone, AddressEntity addressEntity) {
        this.name = name;
        this.telefone = telefone;
        this.addressEntity = addressEntity;
    }

    public OrganizationEntity() {
    }

    

}
