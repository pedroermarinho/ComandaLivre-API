package io.github.pedroermarinho.comandalivreapi.domain.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Table(name = "products")
@DynamicUpdate
@DynamicInsert
@Data
public class ProductEntity extends Auditable {
    private String name;
    private String description;
    private BigDecimal price; 

    @ManyToOne
    private OrganizationEntity organizationEntity;

    @OneToMany(mappedBy = "productEntity")
    private List<ProductOfCommandEntity> productOfCommands;
}
