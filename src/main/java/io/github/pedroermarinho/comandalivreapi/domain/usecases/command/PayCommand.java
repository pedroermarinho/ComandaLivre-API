package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.CommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PayCommand {

    private final CommandRepository commandRepository;
    private final SearchCommand searchCommand;

    public PayCommand(CommandRepository commandRepository, SearchCommand searchCommand) {
        this.commandRepository = commandRepository;
        this.searchCommand = searchCommand;
    }

    public CommandDTO execute(@Nullable UUID id) {
        final List<Validation<UUID>> idValidations = List.of(new NotNullValidation<>());

        idValidations.forEach(validation -> validation.validationThrow(id));

        searchCommand.searchCommandById(id);

        return commandRepository.updatePaidOff(id, true);
    }

}
