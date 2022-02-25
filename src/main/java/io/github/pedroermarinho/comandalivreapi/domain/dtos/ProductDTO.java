package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends AuditableDTO {

    private String name;

    private String description;

    private BigDecimal price;

}
