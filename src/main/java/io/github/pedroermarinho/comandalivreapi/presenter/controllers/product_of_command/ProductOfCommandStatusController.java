package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product_of_command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product_of_command.StatusProductOfCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductOfCommandPathRest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductOfCommandPathRest.PRODUCTOFCOMMAND_STATUS)

public class ProductOfCommandStatusController {

    private final StatusProductOfCommand statusProductOfCommand;

    public ProductOfCommandStatusController(StatusProductOfCommand statusProductOfCommand) {
        this.statusProductOfCommand = statusProductOfCommand;
    }

    @Operation(tags = {"Produto", "Comanda"})
    @PatchMapping("/{id}")
    public ResponseEntity<ProductOfCommandDTO> disableProductOfCommand(@PathVariable UUID id) {
        final ProductOfCommandDTO productOfCommandDTO = statusProductOfCommand.disableProductOfCommand(id);
        return ResponseEntity.ok().body(productOfCommandDTO);
    }
}
