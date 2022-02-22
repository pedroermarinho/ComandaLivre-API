package io.github.pedroermarinho.comandalivreapi.domain.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Table(name = "employees")
@DynamicUpdate
@DynamicInsert
@Data
public class EmployeeEntity extends Auditable {
    
    @Column(unique=true)
    private String registration;

    @ManyToOne
    private UserEntity userEntity;

    @OneToMany(mappedBy = "employeeEntity")
    private List<EmployeeAtOrganizationEntity> employeeAtOrganizations;

    public EmployeeEntity(String registration, UserEntity userEntity) {
        this.registration = registration;
        this.userEntity = userEntity;
    }
    
}
