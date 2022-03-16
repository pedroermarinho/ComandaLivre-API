package io.github.pedroermarinho.comandalivreapi.presenter.controllers.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.command.StatusCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.CommandPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + CommandPathRest.COMMAND_STATUS)
@Tag(name = "Comanda", description = "Operações de comanda")
public class CommandStatusController {

    private final StatusCommand statusCommand;

    public CommandStatusController(StatusCommand statusCommand) {
        this.statusCommand = statusCommand;
    }

    @Operation(summary = "Desativar comanda")
    @PatchMapping("/{id}")
    public ResponseEntity<CommandDTO> disableCommand(@PathVariable UUID id) {
        final CommandDTO command = statusCommand.disableCommand(id);
        return ResponseEntity.ok().body(command);
    }
}
