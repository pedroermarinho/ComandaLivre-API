package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.record.CommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.CommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdateCommand {

    private final CommandRepository commandRepository;

    public UpdateCommand(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Transactional
    public CommandRecord execute(UUID id, CommandRecord commandParam) {
        UtilValidation.idNotNullValidationThrow(id);
        UtilValidation.objectNotNullValidationThrow(commandParam);
        return commandRepository.update(id, commandParam).fold(
                throwable -> {
                    throw throwable;
                },
                result -> result);
    }


}
