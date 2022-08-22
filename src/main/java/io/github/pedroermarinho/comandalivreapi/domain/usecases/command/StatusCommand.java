package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.record.CommandRecord;
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
    public CommandRecord disableCommand(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return commandRepository.disable(id).fold(
                throwable -> {
                    throw throwable;
                },
                result -> result);
    }

    @Transactional
    public CommandRecord enableCommand(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return commandRepository.enable(id).fold(
                throwable -> {
                    throw throwable;
                },
                result -> result);
    }

}
