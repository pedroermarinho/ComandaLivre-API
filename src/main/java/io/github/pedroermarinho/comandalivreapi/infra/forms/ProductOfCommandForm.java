package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Component
@Data
public class ProductOfCommandForm {

    @Schema(description = "Quantidade do produto", example = "1", required = true)
    @NotNull
    private Integer amount;

    @Schema(description = "Id da comanda", example = "1", required = true)
    @NotNull
    private UUID commandId;

    @Schema(description = "Id da produto", example = "1", required = true)
    @NotNull
    private UUID productId;
}
