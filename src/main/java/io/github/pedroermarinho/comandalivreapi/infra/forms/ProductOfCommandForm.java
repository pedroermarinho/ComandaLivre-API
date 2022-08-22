package io.github.pedroermarinho.comandalivreapi.infra.forms;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public record ProductOfCommandForm(

        @Schema(description = "Quantidade do produto", example = "1", required = true)
        @NotNull
        Integer amount,

        @Schema(description = "Id da comanda", example = "ebfe4464-b19c-42b9-a62f-9fc89af29cb3", required = true)
        @NotNull
        UUID commandId,

        @Schema(description = "Id da produto", example = "ebfe4464-b19c-42b9-a62f-9fc89af29cb3", required = true)
        @NotNull
        UUID productId
) implements Serializable {

}
