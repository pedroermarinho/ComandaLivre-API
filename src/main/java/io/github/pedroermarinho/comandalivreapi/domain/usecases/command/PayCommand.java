package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.CommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PayCommand {

    private final CommandRepository commandRepository;
    private final SearchCommand searchCommand;

    public PayCommand(CommandRepository commandRepository, SearchCommand searchCommand) {
        this.commandRepository = commandRepository;
        this.searchCommand = searchCommand;
    }

    public CommandDTO execute(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);

        searchCommand.searchCommandById(id);

        return commandRepository.updatePaidOff(id, true);
    }

}
