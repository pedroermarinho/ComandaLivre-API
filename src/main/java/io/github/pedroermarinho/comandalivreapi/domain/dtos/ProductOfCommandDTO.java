package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductOfCommandDTO extends AuditableDTO {

    private Integer amount;

    private CommandDTO command;

    private ProductDTO product;

}
