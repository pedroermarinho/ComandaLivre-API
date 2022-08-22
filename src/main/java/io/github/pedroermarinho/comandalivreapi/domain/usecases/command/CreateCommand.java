package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.record.CommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.CommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreateCommand {

    private final CommandRepository commandRepository;

    public CreateCommand(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Transactional
    public CommandRecord execute(CommandRecord commandRegister) {
        UtilValidation.objectNotNullValidationThrow(commandRegister);

        final List<Validation<Boolean>> validations = List.of(new NotNullValidation<>());
        validations.forEach(validation -> validation.validationThrow(commandRegister.paidOff()));

        return commandRepository.create(commandRegister).fold(
                throwable -> {
                    throw throwable;
                },
                result -> result);
    }

}
