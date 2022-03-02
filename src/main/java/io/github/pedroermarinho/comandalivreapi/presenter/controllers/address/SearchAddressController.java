package io.github.pedroermarinho.comandalivreapi.presenter.controllers.address;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.address.SearchAddress;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.AddressPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + AddressPathRest.ADDRESS_SEARCH)
public class SearchAddressController {

    private final SearchAddress searchAddress;

    public SearchAddressController(SearchAddress searchAddress) {
        this.searchAddress = searchAddress;
    }

    @Operation(summary = "Lista de endereços")
    @GetMapping
    public ResponseEntity<List<AddressDTO>> searchAddressAll() {
        final List<AddressDTO> address = searchAddress.searchAddressAll();
        return ResponseEntity.ok().body(address);
    }

    @Operation(summary = "Buscar endereço por id")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> searchAddressById(@PathVariable UUID id) {
        final AddressDTO address = searchAddress.searchAddressById(id);
        return ResponseEntity.ok().body(address);
    }

}
