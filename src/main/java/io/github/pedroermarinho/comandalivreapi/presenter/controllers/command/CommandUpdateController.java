package io.github.pedroermarinho.comandalivreapi.presenter.controllers.command;

import io.github.pedroermarinho.comandalivreapi.domain.record.CommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.command.UpdateCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.CommandPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.CommandConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.CommandForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = PathRest.API + PathRest.VERSION + CommandPathRest.COMMAND_UPDATE)
@Tag(name = "Comanda", description = "Operações de comanda")
public class CommandUpdateController {

    private final UpdateCommand updateCommand;
    private final CommandConvert commandConvert;

    public CommandUpdateController(UpdateCommand updateCommand, CommandConvert commandConvert) {
        this.updateCommand = updateCommand;
        this.commandConvert = commandConvert;
    }

    @Operation(summary = "Atualizar comanda")
    @PutMapping("/{id}")
    public ResponseEntity<CommandRecord> updateCommand(@PathVariable UUID id, @Valid @RequestBody CommandForm commandForm) {
        final CommandRecord command = updateCommand.execute(id, commandConvert.convert(commandForm));
        return ResponseEntity.ok().body(command);
    }
}
