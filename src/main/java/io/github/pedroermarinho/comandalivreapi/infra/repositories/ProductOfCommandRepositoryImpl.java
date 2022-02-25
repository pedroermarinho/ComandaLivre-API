package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductOfCommandRepository;
import io.github.pedroermarinho.comandalivreapi.infra.convert.ProductOfCommandConvert;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.ProductOfCommandDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductOfCommandRepositoryImpl implements ProductOfCommandRepository {

    private final ProductOfCommandDataSource productOfCommandDataSource;
    private final ProductOfCommandConvert convert = new ProductOfCommandConvert();

    public ProductOfCommandRepositoryImpl(ProductOfCommandDataSource productOfCommandDataSource) {
        this.productOfCommandDataSource = productOfCommandDataSource;
    }

    @Override
    public List<ProductOfCommandDTO> findAll() {
        return convert.formEntity(productOfCommandDataSource.findAll());
    }

    @Override
    public ProductOfCommandDTO findById(UUID id) {
        return convert.formEntity(productOfCommandDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Produdo da comanda não encontrado! Id: " + id + ", Tipo: "
                                + ProductOfCommandDTO.class.getName())));
    }

    @Override
    public ProductOfCommandDTO create(ProductOfCommandDTO param) {
        return convert.formEntity(productOfCommandDataSource.save(convert.formDTO(param)));
    }

    @Override
    public ProductOfCommandDTO update(UUID id, ProductOfCommandDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public ProductOfCommandDTO disable(UUID id) {
        final ProductOfCommandDTO productOfCommandDTO = findById(id);
        productOfCommandDTO.setStatus(false);
        return convert.formEntity(productOfCommandDataSource.save(convert.formDTO(productOfCommandDTO)));
    }

    @Override
    public ProductOfCommandDTO enable(UUID id) {
        final ProductOfCommandDTO productOfCommandDTO = findById(id);
        productOfCommandDTO.setStatus(true);
        return convert.formEntity(productOfCommandDataSource.save(convert.formDTO(productOfCommandDTO)));
    }

}
