package io.github.pedroermarinho.comandalivreapi.presenter.controllers.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.command.SearchCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.CommandPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + CommandPathRest.COMMAND_SEARCH)
public class SearchCommandController {

    private final SearchCommand searchCommand;

    public SearchCommandController(SearchCommand searchCommand) {
        this.searchCommand = searchCommand;
    }

    @Operation(summary = "Lista de comanda")
    @GetMapping
    public ResponseEntity<List<CommandDTO>> searchCommandAll() {
        final List<CommandDTO> commands = searchCommand.searchCommandAll();
        return ResponseEntity.ok().body(commands);
    }

    @Operation(summary = "Buscar comanda por id")
    @GetMapping("/{id}")
    public ResponseEntity<CommandDTO> searchCommandById(@PathVariable UUID id) {
        final CommandDTO command = searchCommand.searchCommandById(id);
        return ResponseEntity.ok().body(command);
    }

}
