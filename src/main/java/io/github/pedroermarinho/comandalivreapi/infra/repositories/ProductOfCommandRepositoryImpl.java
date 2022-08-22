package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.entities.ProductOfCommandEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.record.ProductOfCommandRecord;
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
    public List<ProductOfCommandRecord> findAll() {
        return productOfCommandDataSource.findAll().stream().map(ProductOfCommandRecord::new).toList();
    }

    @Override
    public Either<RuntimeException, ProductOfCommandRecord> findById(UUID id) {
        return productOfCommandDataSource.findById(id).<Either<RuntimeException, ProductOfCommandRecord>>map(entity -> Either.right(new ProductOfCommandRecord(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Produdo da comanda não encontrado! Id: " + id + ", Tipo: "
                                + ProductOfCommandRecord.class.getName())));
    }

    @Override
    public Either<RuntimeException, ProductOfCommandRecord> create(ProductOfCommandRecord param) {
        return Either.right(new ProductOfCommandRecord(productOfCommandDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, ProductOfCommandRecord> update(UUID id, ProductOfCommandRecord param) {
        throw new NotImplementedException();
    }

    @Override
    public Either<RuntimeException, ProductOfCommandRecord> disable(UUID id) {
        final ProductOfCommandEntity productOfCommandEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                ProductOfCommandRecord::toEntity);
        productOfCommandEntity.setStatus(false);
        return Either.right(new ProductOfCommandRecord(productOfCommandDataSource.save(productOfCommandEntity)));
    }

    @Override
    public Either<RuntimeException, ProductOfCommandRecord> enable(UUID id) {
        final ProductOfCommandEntity productOfCommandEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                ProductOfCommandRecord::toEntity);
        productOfCommandEntity.setStatus(true);
        return Either.right(new ProductOfCommandRecord(productOfCommandDataSource.save(productOfCommandEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(productOfCommandDataSource.count());
    }

}
