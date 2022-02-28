package io.github.pedroermarinho.comandalivreapi.presenter.controllers.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.command.UpdateCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.CommandPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.CommandConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.CommandForm;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + CommandPathRest.COMMAND_UPDATE)
public class UpdateCommandController {

    private final UpdateCommand updateCommand;
    private final CommandConvert convert = new CommandConvert();

    public UpdateCommandController(UpdateCommand updateCommand) {
        this.updateCommand = updateCommand;
    }

    @Operation(summary = "Atualizar comanda")
    @PutMapping("/{id}")
    public ResponseEntity<CommandDTO> updateCommand(@PathVariable UUID id, @Valid @RequestBody CommandForm commandForm) {
        final CommandDTO command = updateCommand.execute(id, convert.fromForm(commandForm));
        return ResponseEntity.ok().body(command);
    }
}
