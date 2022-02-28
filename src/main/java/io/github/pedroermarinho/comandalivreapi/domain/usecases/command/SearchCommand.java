package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.CommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
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
        final List<Validation<UUID>> validations = Arrays.asList(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return commandRepository.findById(id);
    }

    public List<CommandDTO> searchCommandAll() {
        return commandRepository.findAll();
    }

}
