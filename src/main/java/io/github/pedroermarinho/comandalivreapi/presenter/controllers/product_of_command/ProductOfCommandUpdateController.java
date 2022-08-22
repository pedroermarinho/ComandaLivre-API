package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product_of_command;

import io.github.pedroermarinho.comandalivreapi.domain.record.ProductOfCommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product_of_command.UpdateProductOfCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductOfCommandPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.ProductOfCommandConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductOfCommandForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductOfCommandPathRest.PRODUCTOFCOMMAND_UPDATE)
public class ProductOfCommandUpdateController {

    private final UpdateProductOfCommand updateProductOfCommand;
    private final ProductOfCommandConvert commandConvert;

    public ProductOfCommandUpdateController(UpdateProductOfCommand updateProductOfCommand, ProductOfCommandConvert commandConvert) {
        this.updateProductOfCommand = updateProductOfCommand;
        this.commandConvert = commandConvert;
    }

    @Operation(tags = {"Produto", "Comanda"})
    @PutMapping("/{id}")
    public ResponseEntity<ProductOfCommandRecord> disableProductOfCommand(@PathVariable UUID id, @Valid @RequestBody ProductOfCommandForm productofcommandForm) {
        final ProductOfCommandRecord productOfCommandRecord = updateProductOfCommand.execute(id, commandConvert.convert(productofcommandForm));
        return ResponseEntity.ok().body(productOfCommandRecord);
    }
}
