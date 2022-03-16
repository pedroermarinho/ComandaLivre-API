package io.github.pedroermarinho.comandalivreapi.presenter.controllers.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.command.SearchCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.CommandPathRest;
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
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommandDTO.class)))
            )
    })
    @GetMapping
    public ResponseEntity<List<CommandDTO>> searchCommandAll() {
        final List<CommandDTO> commands = searchCommand.searchCommandAll();
        return ResponseEntity.ok().body(commands);
    }

    @Operation(summary = "Buscar comanda por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Comanda não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = CommandDTO.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CommandDTO> searchCommandById(@PathVariable UUID id) {
        final CommandDTO command = searchCommand.searchCommandById(id);
        return ResponseEntity.ok().body(command);
    }

}
