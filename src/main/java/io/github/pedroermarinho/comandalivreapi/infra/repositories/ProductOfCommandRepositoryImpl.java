package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.ProductOfCommandEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductOfCommandRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.ProductOfCommandDataSource;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductOfCommandRepositoryImpl implements ProductOfCommandRepository {

    private final ProductOfCommandDataSource productOfCommandDataSource;

    public ProductOfCommandRepositoryImpl(ProductOfCommandDataSource productOfCommandDataSource) {
        this.productOfCommandDataSource = productOfCommandDataSource;
    }

    @Override
    public List<ProductOfCommandDTO> findAll() {
        return productOfCommandDataSource.findAll().stream().map(ProductOfCommandDTO::new).toList();
    }

    @Override
    public Either<RuntimeException, ProductOfCommandDTO> findById(UUID id) {
        return productOfCommandDataSource.findById(id).<Either<RuntimeException, ProductOfCommandDTO>>map(entity -> Either.right(new ProductOfCommandDTO(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Produdo da comanda não encontrado! Id: " + id + ", Tipo: "
                                + ProductOfCommandDTO.class.getName())));
    }

    @Override
    public Either<RuntimeException, ProductOfCommandDTO> create(ProductOfCommandDTO param) {
        return Either.right(new ProductOfCommandDTO(productOfCommandDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, ProductOfCommandDTO> update(UUID id, ProductOfCommandDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public Either<RuntimeException, ProductOfCommandDTO> disable(UUID id) {
        final ProductOfCommandEntity productOfCommandEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                ProductOfCommandDTO::toEntity);
        productOfCommandEntity.setStatus(false);
        return Either.right(new ProductOfCommandDTO(productOfCommandDataSource.save(productOfCommandEntity)));
    }

    @Override
    public Either<RuntimeException, ProductOfCommandDTO> enable(UUID id) {
        final ProductOfCommandEntity productOfCommandEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                ProductOfCommandDTO::toEntity);
        productOfCommandEntity.setStatus(true);
        return Either.right(new ProductOfCommandDTO(productOfCommandDataSource.save(productOfCommandEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(productOfCommandDataSource.count());
    }

}
