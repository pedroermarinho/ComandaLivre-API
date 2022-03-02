package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@Data
public class ProductOfCommandForm {

    @NotNull
    private Integer amount;

    private String commandId;

    private String productId;
}
