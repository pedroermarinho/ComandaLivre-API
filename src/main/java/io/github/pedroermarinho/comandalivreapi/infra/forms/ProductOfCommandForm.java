package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Component
@Data
public class ProductOfCommandForm {

    @NotNull
    private Integer amount;

    private UUID commandId;

    private UUID productId;
}
