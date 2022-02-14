package io.github.pedroermarinho.comandalivreapi.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Entity
@Table(name = "user")
@DynamicUpdate
@DynamicInsert
@Data
public class User extends Auditable{

    private String name;

    @Column(unique=true)
    private String email;

    @Column(unique=true)
    private String username;

    private String password;

    private boolean status;


    public User() {
    }


    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    
}
