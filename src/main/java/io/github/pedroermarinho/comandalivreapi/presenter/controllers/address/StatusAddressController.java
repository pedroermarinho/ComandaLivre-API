package io.github.pedroermarinho.comandalivreapi.presenter.controllers.address;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.address.StatusAddress;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.AddressPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + AddressPathRest.ADDRESS_STATUS)
public class StatusAddressController {

    private final StatusAddress statusAddress;

    public StatusAddressController(StatusAddress statusAddress) {
        this.statusAddress = statusAddress;
    }

    @Operation(summary = "Desativar endereço")
    @PatchMapping("/{id}")
    public ResponseEntity<AddressDTO> disableAddress(@PathVariable UUID id) {
        final AddressDTO address = statusAddress.disableAddress(id);
        return ResponseEntity.ok().body(address);
    }
}
