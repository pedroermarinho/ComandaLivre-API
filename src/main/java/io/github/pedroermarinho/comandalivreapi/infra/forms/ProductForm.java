package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Component
@Data
public class ProductForm {

    @Schema(description = "Nome do produto", example = "Coca-cola", required = true)
    @NotBlank
    @Size(min = 2, max = 255)
    private String name;

    @Schema(description = "Descrição do produto", example = "Coca-Cola é um refrigerante carbonado", required = false)
    @Size(max = 255)
    private String description;

    @Schema(description = "Valor do produto", example = "15", required = true)
    @NotNull
    private BigDecimal price;

    @Schema(description = "Organização responsável pela produto", example = "15", required = true)
    @NotNull
    private UUID organizationId;
}
