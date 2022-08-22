package io.github.pedroermarinho.comandalivreapi.presenter.controllers.command;

import io.github.pedroermarinho.comandalivreapi.domain.record.CommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.command.CreateCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.CommandPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.CommandConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.CommandForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = PathRest.API + PathRest.VERSION + CommandPathRest.COMMAND_REGISTER)
@Tag(name = "Comanda", description = "Operações de comanda")
public class CommandRegisterController {

    private final CreateCommand registerCommand;
    private final CommandConvert commandConvert;

    public CommandRegisterController(CreateCommand registerCommand, CommandConvert commandConvert) {
        this.registerCommand = registerCommand;
        this.commandConvert = commandConvert;
    }

    @Operation(summary = "Cadastrar nova comanda")
    @PostMapping
    public ResponseEntity<CommandRecord> registerCommand(CommandForm commandForm) {
        final CommandRecord command = registerCommand.execute(commandConvert.convert(commandForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(command.id())
                .toUri();

        return ResponseEntity.created(uri).body(command);
    }

}
