package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductOfCommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RemoveProductionToCommand {

    private final ProductOfCommandRepository productofcommandRepository;

    public RemoveProductionToCommand(ProductOfCommandRepository productofcommandRepository) {
        this.productofcommandRepository = productofcommandRepository;
    }

    @Transactional
    public ProductOfCommandDTO execute(@Nullable UUID id) {
        UtilValidation.idNotNullValidationThrow(id);

        return productofcommandRepository.disable(id);
    }
}
