package io.github.pedroermarinho.comandalivreapi.domain.usecases.product;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
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
    public ProductDTO disableProduct(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return productRepository.disable(id);
    }

    @Transactional
    public ProductDTO enableProduct(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return productRepository.enable(id);
    }
}
