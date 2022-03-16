package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product_of_command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product_of_command.RegisterProductOfCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductOfCommandPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.ProductOfCommandConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductOfCommandForm;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductOfCommandPathRest.PRODUCTOFCOMMAND_REGISTER)
public class ProductOfCommandRegisterController {

    private final RegisterProductOfCommand registerProductOfCommand;
    private final ProductOfCommandConvert commandConvert;

    public ProductOfCommandRegisterController(RegisterProductOfCommand registerProductOfCommand, ProductOfCommandConvert commandConvert) {
        this.registerProductOfCommand = registerProductOfCommand;
        this.commandConvert = commandConvert;
    }

    @Operation(tags = {"Produto", "Comanda"})
    @PostMapping
    public ResponseEntity<ProductOfCommandDTO> registerProductOfCommand(ProductOfCommandForm productofcommandForm) {
        final ProductOfCommandDTO productOfCommandDTO = registerProductOfCommand.execute(commandConvert.convert(productofcommandForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productOfCommandDTO.id())
                .toUri();

        return ResponseEntity.created(uri).body(productOfCommandDTO);
    }

}
