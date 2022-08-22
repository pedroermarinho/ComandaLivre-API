package io.github.pedroermarinho.comandalivreapi.presenter.controllers.address;

import io.github.pedroermarinho.comandalivreapi.domain.record.AddressRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.address.StatusAddress;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.AddressPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = PathRest.API + PathRest.VERSION + AddressPathRest.ADDRESS_STATUS)
@Tag(name = "Endereço", description = "Operações de endereço")
public class AddressStatusController {

    private final StatusAddress statusAddress;

    public AddressStatusController(StatusAddress statusAddress) {
        this.statusAddress = statusAddress;
    }

    @Operation(summary = "Desativar endereço")
    @PatchMapping("/{id}")
    public ResponseEntity<AddressRecord> disableAddress(@PathVariable UUID id) {
        final AddressRecord address = statusAddress.disableAddress(id);
        return ResponseEntity.ok().body(address);
    }
}
