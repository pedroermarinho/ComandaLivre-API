package io.github.pedroermarinho.comandalivreapi.domain.usecases.product_of_command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductOfCommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class StatusProductOfCommand {

    private final ProductOfCommandRepository productofcommandRepository;

    public StatusProductOfCommand(ProductOfCommandRepository productofcommandRepository) {
        this.productofcommandRepository = productofcommandRepository;
    }

    @Transactional
    public ProductOfCommandDTO disableProductOfCommand(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return productofcommandRepository.disable(id);
    }

    @Transactional
    public ProductOfCommandDTO enableProductOfCommand(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return productofcommandRepository.enable(id);
    }
}
