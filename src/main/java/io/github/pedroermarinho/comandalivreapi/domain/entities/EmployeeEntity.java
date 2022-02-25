package io.github.pedroermarinho.comandalivreapi.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@DynamicUpdate
@DynamicInsert
@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeEntity extends Auditable {

    @Column(unique = true)
    private String registration;

    @ManyToOne
    private UserEntity userEntity;

    @OneToMany(mappedBy = "employeeEntity")
    private List<EmployeeAtOrganizationEntity> employeeAtOrganizations = new ArrayList<>();


    public EmployeeEntity() {
    }


    public EmployeeEntity(String registration, UserEntity userEntity) {
        this.registration = registration;
        this.userEntity = userEntity;
    }

}
