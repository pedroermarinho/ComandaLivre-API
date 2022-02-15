package io.github.pedroermarinho.comandalivreapi.domain.entities;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "user")
@DynamicUpdate
@DynamicInsert
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

    /* get and set*/ 

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isStatus() {
        return status;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }

    

    
    
}
