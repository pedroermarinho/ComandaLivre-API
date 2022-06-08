package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.CommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchCommand {

    private final CommandRepository commandRepository;

    public SearchCommand(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    public CommandDTO searchCommandById(@Nullable UUID id) {
        UtilValidation.idNotNullValidationThrow(id);

        final var result = commandRepository.findById(id);

        UtilValidation.statusEnableValidationThrow(result.status());

        return result;
    }

    public List<CommandDTO> searchCommandAll() {
        return commandRepository.findAll().stream().filter(CommandDTO::status).toList();
    }
}
