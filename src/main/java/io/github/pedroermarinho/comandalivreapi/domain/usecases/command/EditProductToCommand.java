package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

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
public class EditProductToCommand {

    private final ProductOfCommandRepository productofcommandRepository;

    public EditProductToCommand(ProductOfCommandRepository productofcommandRepository) {
        this.productofcommandRepository = productofcommandRepository;
    }

    @Transactional
    public ProductOfCommandDTO execute(UUID id, ProductOfCommandDTO payload) {

        final List<Validation<UUID>> idValidations = List.of(new NotNullValidation<>());

        idValidations.forEach(validation -> validation.validationThrow(id));

        return productofcommandRepository.update(id, payload);
    }
}
