package io.github.pedroermarinho.comandalivreapi.presenter.controllers.command;

import io.github.pedroermarinho.comandalivreapi.domain.record.CommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.command.SearchCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.CommandPathRest;
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
@RequestMapping(value = PathRest.API + PathRest.VERSION + CommandPathRest.COMMAND_SEARCH)
@Tag(name = "Comanda", description = "Operações de comanda")
public class CommandSearchController {

    private final SearchCommand searchCommand;

    public CommandSearchController(SearchCommand searchCommand) {
        this.searchCommand = searchCommand;
    }

    @Operation(summary = "Lista de comanda")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommandRecord.class)))
            )
    })
    @GetMapping
    public ResponseEntity<List<CommandRecord>> searchCommandAll() {
        final List<CommandRecord> commands = searchCommand.searchCommandAll();
        return ResponseEntity.ok().body(commands);
    }

    @Operation(summary = "Buscar comanda por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Comanda não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = CommandRecord.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CommandRecord> searchCommandById(@PathVariable UUID id) {
        final CommandRecord command = searchCommand.searchCommandById(id);
        return ResponseEntity.ok().body(command);
    }

}
