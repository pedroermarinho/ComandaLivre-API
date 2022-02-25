package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
public class ProductForm {

    private String name;

    private String description;

    private BigDecimal price;
}
