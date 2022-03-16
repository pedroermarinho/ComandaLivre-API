package io.github.pedroermarinho.comandalivreapi.presenter.controllers.address;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.address.SearchAddress;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.AddressPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + AddressPathRest.ADDRESS_SEARCH)
@Tag(name = "Endereço", description = "Operações de endereço")
public class AddressSearchController {

    private final SearchAddress searchAddress;

    public AddressSearchController(SearchAddress searchAddress) {
        this.searchAddress = searchAddress;
    }

    @Operation(summary = "Lista de endereços")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommandDTO.class)))
            )
    })
    @GetMapping
    public ResponseEntity<List<AddressDTO>> searchAddressAll() {
        final List<AddressDTO> address = searchAddress.searchAddressAll();
        return ResponseEntity.ok().body(address);
    }

    @Operation(summary = "Buscar endereço por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = CommandDTO.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> searchAddressById(@PathVariable UUID id) {
        final AddressDTO address = searchAddress.searchAddressById(id);
        return ResponseEntity.ok().body(address);
    }

}
