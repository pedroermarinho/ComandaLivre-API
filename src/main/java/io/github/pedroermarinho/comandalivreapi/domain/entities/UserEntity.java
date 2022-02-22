package io.github.pedroermarinho.comandalivreapi.domain.entities;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Table(name = "users")
@DynamicUpdate
@DynamicInsert
@Data
public class UserEntity extends Auditable{

    private String name;

    @Column(unique=true)
    private String email;

    @Column(unique=true)
    private String username;

    private String password;

    private String telefone; 

    @OneToMany(mappedBy = "userEntity")
    private List<EmployeeEntity> employees; 

    @OneToMany(mappedBy = "userEntity")
    private List<CommandEntity> commands;


    public UserEntity() {
    }


    public UserEntity(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    
}
