package io.github.pedroermarinho.comandalivreapi.presenter.controllers.address;

import io.github.pedroermarinho.comandalivreapi.domain.record.AddressRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.address.UpdateAddress;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.AddressPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.AddressConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.AddressForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = PathRest.API + PathRest.VERSION + AddressPathRest.ADDRESS_UPDATE)
@Tag(name = "Endereço", description = "Operações de endereço")
public class AddressUpdateController {

    private final UpdateAddress updateAddress;
    private final AddressConvert addressConvert;

    public AddressUpdateController(UpdateAddress updateAddress, AddressConvert addressConvert) {
        this.updateAddress = updateAddress;
        this.addressConvert = addressConvert;
    }

    @Operation(summary = "Atualizar endereço")
    @PutMapping("/{id}")
    public ResponseEntity<AddressRecord> updateAddress(@PathVariable UUID id, @Valid @RequestBody AddressForm addressForm) {
        final AddressRecord address = updateAddress.execute(id, addressConvert.convert(addressForm));
        return ResponseEntity.ok().body(address);
    }
}
