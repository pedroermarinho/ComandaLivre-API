package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product_of_command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product_of_command.SearchProductOfCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductOfCommandPathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductOfCommandPathRest.PRODUCTOFCOMMAND_SEARCH)
public class ProductOfCommandSearchController {

    private final SearchProductOfCommand searchProductOfCommand;

    public ProductOfCommandSearchController(SearchProductOfCommand searchProductOfCommand) {
        this.searchProductOfCommand = searchProductOfCommand;
    }

    @Operation(tags = {"Produto", "Comanda"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = ProductOfCommandDTO.class))
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<ProductOfCommandDTO>> searchProductOfCommandAll() {
        final List<ProductOfCommandDTO> productOfCommandDTOList = searchProductOfCommand.searchProductOfCommandAll();
        return ResponseEntity.ok().body(productOfCommandDTOList);
    }

    @Operation(tags = {"Produto", "Comanda"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = ProductOfCommandDTO.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductOfCommandDTO> searchProductOfCommandById(@PathVariable UUID id) {
        final ProductOfCommandDTO productOfCommandDTO = searchProductOfCommand.searchProductOfCommandById(id);
        return ResponseEntity.ok().body(productOfCommandDTO);
    }

}
