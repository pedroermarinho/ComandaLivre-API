package io.github.pedroermarinho.comandalivreapi.domain.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;



@Entity
@Table(name = "commands")
@DynamicUpdate
@DynamicInsert
@Data
public class CommandEntity extends Auditable{
    
    private Boolean paidOff;
    private String identification;

    @ManyToOne
    private UserEntity userEntity;


    @OneToMany(mappedBy = "commandEntity")
    private List<ProductOfCommandEntity> productOfCommands;

    public CommandEntity() {
    }

    public CommandEntity(Boolean paidOff, String identification, UserEntity userEntity) {
        this.paidOff = paidOff;
        this.identification = identification;
        this.userEntity = userEntity;
    }

    

}
