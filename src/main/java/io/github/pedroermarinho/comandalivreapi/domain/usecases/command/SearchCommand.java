package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.CommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.ObjectDisabledValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class SearchCommand {

    private final CommandRepository commandRepository;

    public SearchCommand(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    public CommandDTO searchCommandById(UUID id) {
        final List<Validation<UUID>> validations = List.of(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        final var result =commandRepository.findById(id);

        final List<Validation<Boolean>> statusValidations = List.of(
                new NotNullValidation<>(),
                new ObjectDisabledValidation()
        );

        statusValidations.forEach(validation -> validation.validationThrow(result.status()));

        return result;
    }

    public List<CommandDTO> searchCommandAll() {
        return commandRepository.findAll().stream().filter(CommandDTO::status).toList();
    }

}
