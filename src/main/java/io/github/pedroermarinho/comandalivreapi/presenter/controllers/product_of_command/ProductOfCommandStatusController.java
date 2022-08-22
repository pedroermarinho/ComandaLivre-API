package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product_of_command;

import io.github.pedroermarinho.comandalivreapi.domain.record.ProductOfCommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product_of_command.StatusProductOfCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductOfCommandPathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductOfCommandPathRest.PRODUCTOFCOMMAND_STATUS)
public class ProductOfCommandStatusController {

    private final StatusProductOfCommand statusProductOfCommand;

    public ProductOfCommandStatusController(StatusProductOfCommand statusProductOfCommand) {
        this.statusProductOfCommand = statusProductOfCommand;
    }

    @Operation(tags = {"Produto", "Comanda"})
    @PatchMapping("/{id}")
    public ResponseEntity<ProductOfCommandRecord> disableProductOfCommand(@PathVariable UUID id) {
        final ProductOfCommandRecord productOfCommandRecord = statusProductOfCommand.disableProductOfCommand(id);
        return ResponseEntity.ok().body(productOfCommandRecord);
    }
}
