package io.github.pedroermarinho.comandalivreapi.domain.usecases.product_of_command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductOfCommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class SearchAddress {

    private final ProductOfCommandRepository productofcommandRepository;

    public SearchAddress(ProductOfCommandRepository productofcommandRepository) {
        this.productofcommandRepository = productofcommandRepository;
    }

    public ProductOfCommandDTO searchProductOfCommandById(UUID id) {
        final List<Validation<UUID>> validations = Arrays.asList(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return productofcommandRepository.findById(id);
    }

    public List<ProductOfCommandDTO> searchProductOfCommandAll() {
        return productofcommandRepository.findAll();
    }

}
