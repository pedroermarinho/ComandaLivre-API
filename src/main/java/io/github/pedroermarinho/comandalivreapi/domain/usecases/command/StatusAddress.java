package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.CommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class StatusAddress {

    private final CommandRepository commandRepository;

    public StatusAddress(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Transactional
    public CommandDTO disableCommand(UUID id) {
        final List<Validation<UUID>> validations = Arrays.asList(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return commandRepository.disable(id);
    }

    @Transactional
    public CommandDTO enableCommand(UUID id) {
        final List<Validation<UUID>> validations = Arrays.asList(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return commandRepository.enable(id);
    }
}
