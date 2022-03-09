package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product_of_command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product_of_command.RegisterProductOfCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductOfCommandPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.ProductOfCommandConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductOfCommandForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductOfCommandPathRest.PRODUCTOFCOMMAND_REGISTER)
public class RegisterProductOfCommandController {

    private final RegisterProductOfCommand registerProductOfCommand;
    private final ProductOfCommandConvert convert = new ProductOfCommandConvert(searchCommand, searchProduct);

    public RegisterProductOfCommandController(RegisterProductOfCommand registerProductOfCommand) {
        this.registerProductOfCommand = registerProductOfCommand;
    }

    @PostMapping
    public ResponseEntity<ProductOfCommandDTO> registerProductOfCommand(ProductOfCommandForm productofcommandForm) {
        final ProductOfCommandDTO productofcommand = registerProductOfCommand.execute(convert.fromForm(productofcommandForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productofcommand.getId())
                .toUri();

        return ResponseEntity.created(uri).body(productofcommand);
    }

}
