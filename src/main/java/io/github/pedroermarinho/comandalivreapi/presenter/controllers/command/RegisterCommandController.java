package io.github.pedroermarinho.comandalivreapi.presenter.controllers.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.command.RegisterCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.CommandPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.CommandConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.CommandForm;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + CommandPathRest.COMMAND_REGISTER)
public class RegisterCommandController {

    private final RegisterCommand registerCommand;
    private final CommandConvert convert = new CommandConvert();

    public RegisterCommandController(RegisterCommand registerCommand) {
        this.registerCommand = registerCommand;
    }

    @Operation(summary = "Cadastrar nova comanda")
    @PostMapping
    public ResponseEntity<CommandDTO> registerCommand(CommandForm commandForm) {
        final CommandDTO command = registerCommand.execute(convert.fromForm(commandForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(command.getId())
                .toUri();

        return ResponseEntity.created(uri).body(command);
    }

}
