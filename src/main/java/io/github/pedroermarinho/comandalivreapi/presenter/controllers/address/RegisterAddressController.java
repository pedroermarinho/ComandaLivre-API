package io.github.pedroermarinho.comandalivreapi.presenter.controllers.address;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.address.RegisterAddress;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.AddressPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.AddressConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.AddressForm;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + AddressPathRest.ADDRESS_REGISTER)
public class RegisterAddressController {

    private final RegisterAddress registerAddress;
    private final AddressConvert convert = new AddressConvert();

    public RegisterAddressController(RegisterAddress registerAddress) {
        this.registerAddress = registerAddress;
    }

    @Operation(summary = "Cadastrar endereço")
    @PostMapping
    public ResponseEntity<AddressDTO> registerAddress(AddressForm addressForm) {
        final AddressDTO address = registerAddress.execute(convert.fromForm(addressForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(address.getId())
                .toUri();

        return ResponseEntity.created(uri).body(address);
    }

}
