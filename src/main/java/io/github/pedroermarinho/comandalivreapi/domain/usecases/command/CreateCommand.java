package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.CommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.jetbrains.annotations.Nullable;
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
    public CommandDTO execute(@Nullable CommandDTO commandRegister) {
        final List<Validation<Boolean>> validations = List.of(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(commandRegister.paidOff()));

        return commandRepository.create(commandRegister);
    }

}
