package io.github.pedroermarinho.comandalivreapi.presenter.controllers.address;

import io.github.pedroermarinho.comandalivreapi.domain.record.AddressRecord;
import io.github.pedroermarinho.comandalivreapi.domain.record.CommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.address.SearchAddress;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.AddressPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
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
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommandRecord.class)))
            )
    })
    @GetMapping
    public ResponseEntity<List<AddressRecord>> searchAddressAll() {
        final List<AddressRecord> address = searchAddress.searchAddressAll();
        return ResponseEntity.ok().body(address);
    }

    @Operation(summary = "Buscar endereço por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = CommandRecord.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<AddressRecord> searchAddressById(@PathVariable UUID id) {
        final AddressRecord address = searchAddress.searchAddressById(id);
        return ResponseEntity.ok().body(address);
    }

}
