package io.github.pedroermarinho.comandalivreapi.presenter.controllers.command;

import io.github.pedroermarinho.comandalivreapi.domain.record.CommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.command.StatusCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.CommandPathRest;
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
@RequestMapping(value = PathRest.API + PathRest.VERSION + CommandPathRest.COMMAND_STATUS)
@Tag(name = "Comanda", description = "Operações de comanda")
public class CommandStatusController {

    private final StatusCommand statusCommand;

    public CommandStatusController(StatusCommand statusCommand) {
        this.statusCommand = statusCommand;
    }

    @Operation(summary = "Desativar comanda")
    @PatchMapping("/{id}")
    public ResponseEntity<CommandRecord> disableCommand(@PathVariable UUID id) {
        final CommandRecord command = statusCommand.disableCommand(id);
        return ResponseEntity.ok().body(command);
    }
}
