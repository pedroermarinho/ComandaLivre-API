package io.github.pedroermarinho.comandalivreapi.domain.usecases.product_of_command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductOfCommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UpdateAddress {

    private final ProductOfCommandRepository productofcommandRepository;

    public UpdateAddress(ProductOfCommandRepository productofcommandRepository) {
        this.productofcommandRepository = productofcommandRepository;
    }

    @Transactional
    public ProductOfCommandDTO execute(UUID id, ProductOfCommandDTO productofcommandParam) {

        final List<Validation<UUID>> idValidations = Arrays.asList(new NotNullValidation<>());

        idValidations.forEach(validation -> validation.validationThrow(id));

        return productofcommandRepository.update(id, productofcommandParam);
    }
}
