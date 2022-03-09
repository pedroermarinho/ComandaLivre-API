package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product_of_command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product_of_command.UpdateProductOfCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductOfCommandPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.ProductOfCommandConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductOfCommandForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductOfCommandPathRest.PRODUCTOFCOMMAND_UPDATE)
public class UpdateProductOfCommandController {

    private final UpdateProductOfCommand updateProductOfCommand;
    private final ProductOfCommandConvert convert = new ProductOfCommandConvert(searchCommand, searchProduct);

    public UpdateProductOfCommandController(UpdateProductOfCommand updateProductOfCommand) {
        this.updateProductOfCommand = updateProductOfCommand;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOfCommandDTO> disableProductOfCommand(@PathVariable UUID id, @Valid @RequestBody ProductOfCommandForm productofcommandForm) {
        final ProductOfCommandDTO productofcommand = updateProductOfCommand.execute(id, convert.fromForm(productofcommandForm));
        return ResponseEntity.ok().body(productofcommand);
    }
}
