package io.github.pedroermarinho.comandalivreapi.domain.usecases.product_of_command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductOfCommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterProductOfCommand {

    private final ProductOfCommandRepository productofcommandRepository;

    public RegisterProductOfCommand(ProductOfCommandRepository productofcommandRepository) {
        this.productofcommandRepository = productofcommandRepository;
    }

    @Transactional
    public ProductOfCommandDTO execute(ProductOfCommandDTO payload) {
        UtilValidation.objectNotNullValidationThrow(payload);
        UtilValidation.idNotNullValidationThrow(payload.product().id());
        return productofcommandRepository.create(payload).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

}
