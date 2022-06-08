package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.CommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class StatusCommand {

    private final CommandRepository commandRepository;

    public StatusCommand(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Transactional
    public CommandDTO disableCommand(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return commandRepository.disable(id);
    }

    @Transactional
    public CommandDTO enableCommand(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return commandRepository.enable(id);
    }

}
