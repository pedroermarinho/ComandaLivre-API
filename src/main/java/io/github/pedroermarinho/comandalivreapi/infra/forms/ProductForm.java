package io.github.pedroermarinho.comandalivreapi.infra.forms;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductForm(

        @Schema(description = "Nome do produto", example = "Coca-cola", required = true)
        @NotBlank
        @Size(min = 2, max = 255)
        String name,

        @Schema(description = "Descrição do produto", example = "Coca-Cola é um refrigerante carbonado", required = false)
        @Size(max = 255)
        String description,

        @Schema(description = "Valor do produto", example = "15", required = true)
        @NotNull
        BigDecimal price,

        @Schema(description = "Organização responsável pela produto", example = "ebfe4464-b19c-42b9-a62f-9fc89af29cb3", required = true)
        @NotNull
        UUID organizationId
) implements Serializable {

}
