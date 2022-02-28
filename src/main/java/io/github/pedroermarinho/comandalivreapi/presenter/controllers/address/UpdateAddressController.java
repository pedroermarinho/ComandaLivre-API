package io.github.pedroermarinho.comandalivreapi.presenter.controllers.address;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.address.UpdateAddress;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.AddressPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.AddressConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.AddressForm;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + AddressPathRest.ADDRESS_UPDATE)
public class UpdateAddressController {

    private final UpdateAddress updateAddress;
    private final AddressConvert convert = new AddressConvert();

    public UpdateAddressController(UpdateAddress updateAddress) {
        this.updateAddress = updateAddress;
    }

    @Operation(summary = "Atualizar endereço")
    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable UUID id, @Valid @RequestBody AddressForm addressForm) {
        final AddressDTO address = updateAddress.execute(id, convert.fromForm(addressForm));
        return ResponseEntity.ok().body(address);
    }
}
