package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductOfCommandForm {

    private Integer amount;

    private String commandId;

    private String productId;
}
