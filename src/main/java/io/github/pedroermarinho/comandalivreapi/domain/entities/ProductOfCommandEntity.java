package io.github.pedroermarinho.comandalivreapi.domain.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;


@Entity
@Table(name = "product_of_command")
@DynamicUpdate
@DynamicInsert
@Data
public class ProductOfCommandEntity  extends Auditable{

    private Integer amount;

    @ManyToOne
    private CommandEntity commandEntity;

    @ManyToOne
    private ProductEntity productEntity;


    public ProductOfCommandEntity() {
    }

    public ProductOfCommandEntity(Integer amount, CommandEntity commandEntity, ProductEntity productEntity) {
        this.amount = amount;
        this.commandEntity = commandEntity;
        this.productEntity = productEntity;
    }

    
    
    
}
