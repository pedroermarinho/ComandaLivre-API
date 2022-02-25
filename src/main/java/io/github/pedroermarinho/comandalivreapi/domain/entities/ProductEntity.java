package io.github.pedroermarinho.comandalivreapi.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@DynamicUpdate
@DynamicInsert
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends Auditable {

    private String name;
    private String description;
    private BigDecimal price;

    @ManyToOne
    private OrganizationEntity organizationEntity;

    @OneToMany(mappedBy = "productEntity")
    private List<ProductOfCommandEntity> productOfCommands = new ArrayList<>();
}
