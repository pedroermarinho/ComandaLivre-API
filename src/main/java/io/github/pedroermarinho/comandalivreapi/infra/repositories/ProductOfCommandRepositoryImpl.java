package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.ProductOfCommandEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductOfCommandRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.ProductOfCommandDataSource;
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
    public ProductOfCommandDTO findById(UUID id) {
        return new ProductOfCommandDTO(productOfCommandDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Produdo da comanda não encontrado! Id: " + id + ", Tipo: "
                                + ProductOfCommandDTO.class.getName())));
    }

    @Override
    public ProductOfCommandDTO create(ProductOfCommandDTO param) {
        return new ProductOfCommandDTO(productOfCommandDataSource.save(param.toEntity()));
    }

    @Override
    public ProductOfCommandDTO update(UUID id, ProductOfCommandDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public ProductOfCommandDTO disable(UUID id) {
        final ProductOfCommandEntity productOfCommandEntity = findById(id).toEntity();
        productOfCommandEntity.setStatus(false);
        return new ProductOfCommandDTO(productOfCommandDataSource.save(productOfCommandEntity));
    }

    @Override
    public ProductOfCommandDTO enable(UUID id) {
        final ProductOfCommandEntity productOfCommandEntity = findById(id).toEntity();
        productOfCommandEntity.setStatus(true);
        return new ProductOfCommandDTO(productOfCommandDataSource.save(productOfCommandEntity));
    }

    @Override
    public long count() {
        return productOfCommandDataSource.count();
    }

}
