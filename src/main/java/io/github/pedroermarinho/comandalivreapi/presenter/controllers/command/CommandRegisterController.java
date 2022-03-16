package io.github.pedroermarinho.comandalivreapi.presenter.controllers.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.command.RegisterCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.CommandPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.CommandConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.CommandForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + CommandPathRest.COMMAND_REGISTER)
@Tag(name = "Comanda", description = "Operações de comanda")
public class CommandRegisterController {

    private final RegisterCommand registerCommand;
    private final CommandConvert commandConvert;

    public CommandRegisterController(RegisterCommand registerCommand, CommandConvert commandConvert) {
        this.registerCommand = registerCommand;
        this.commandConvert = commandConvert;
    }

    @Operation(summary = "Cadastrar nova comanda")
    @PostMapping
    public ResponseEntity<CommandDTO> registerCommand(CommandForm commandForm) {
        final CommandDTO command = registerCommand.execute(commandConvert.convert(commandForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(command.id())
                .toUri();

        return ResponseEntity.created(uri).body(command);
    }

}
