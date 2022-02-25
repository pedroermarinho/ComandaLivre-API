package io.github.pedroermarinho.comandalivreapi.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "commands")
@DynamicUpdate
@DynamicInsert
@Data
@EqualsAndHashCode(callSuper = true)
public class CommandEntity extends Auditable {

    private Boolean paidOff;
    private String identification;

    @ManyToOne
    private UserEntity userEntity;


    @OneToMany(mappedBy = "commandEntity")
    private List<ProductOfCommandEntity> productOfCommands = new ArrayList<>();

    public CommandEntity() {
    }

    public CommandEntity(Boolean paidOff, String identification, UserEntity userEntity) {
        this.paidOff = paidOff;
        this.identification = identification;
        this.userEntity = userEntity;
    }


}
