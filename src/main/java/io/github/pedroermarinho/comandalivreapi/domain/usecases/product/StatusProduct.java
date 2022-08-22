package io.github.pedroermarinho.comandalivreapi.domain.usecases.product;

import io.github.pedroermarinho.comandalivreapi.domain.record.ProductRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class StatusProduct {

    private final ProductRepository productRepository;

    public StatusProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductRecord disableProduct(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return productRepository.disable(id).fold(
                throwable -> {
                    throw throwable;
                },
                result -> result);
    }

    @Transactional
    public ProductRecord enableProduct(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return productRepository.enable(id).fold(
                throwable -> {
                    throw throwable;
                },
                result -> result);
    }
}
