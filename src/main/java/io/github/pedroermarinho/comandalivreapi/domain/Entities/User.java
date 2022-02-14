package io.github.pedroermarinho.comandalivreapi.domain.Entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class User extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    @Column(unique=true)
    private String email;

    @Column(unique=true)
    private String username;

    private String password;

    private boolean status;

}
