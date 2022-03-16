package io.github.pedroermarinho.comandalivreapi.presenter.controllers.address;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.address.RegisterAddress;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.AddressPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.AddressConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.AddressForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + AddressPathRest.ADDRESS_REGISTER)
@Tag(name = "Endereço", description = "Operações de endereço")
public class AddressRegisterController {

    private final RegisterAddress registerAddress;
    private final AddressConvert addressConvert;

    public AddressRegisterController(RegisterAddress registerAddress, AddressConvert addressConvert) {
        this.registerAddress = registerAddress;
        this.addressConvert = addressConvert;
    }

    @Operation(summary = "Cadastrar endereço")
    @PostMapping
    public ResponseEntity<AddressDTO> registerAddress(AddressForm addressForm) {
        final AddressDTO address = registerAddress.execute(addressConvert.convert(addressForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(address.id())
                .toUri();

        return ResponseEntity.created(uri).body(address);
    }

}
