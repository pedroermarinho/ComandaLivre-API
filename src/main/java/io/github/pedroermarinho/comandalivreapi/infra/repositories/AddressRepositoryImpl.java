package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.AddressEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.AddressRepository;
import io.github.pedroermarinho.comandalivreapi.infra.convert.AddressConvert;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.AddressDataSource;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AddressRepositoryImpl implements AddressRepository {

    private final AddressDataSource addressDataSource;

    public AddressRepositoryImpl(AddressDataSource addressDataSource) {
        this.addressDataSource = addressDataSource;
    }

    @Override
    public List<AddressDTO> findAll() {
        return addressDataSource.findAll().stream().map(AddressDTO::new).toList();
    }

    @Override
    public Either<RuntimeException, AddressDTO> findById(UUID id) {
        return addressDataSource.findById(id)
                .<Either<RuntimeException, AddressDTO>>map(entity -> Either.right(new AddressDTO(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Endereço não encontrado! Id: " + id + ", Tipo: " + AddressDTO.class.getName())));
    }

    @Override
    public Either<RuntimeException, AddressDTO> create(AddressDTO param) {
        return Either.right(new AddressDTO(addressDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, AddressDTO> update(UUID id, AddressDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public Either<RuntimeException, AddressDTO> disable(UUID id) {
        final AddressEntity addressEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                AddressDTO::toEntity);
        addressEntity.setStatus(false);
        return Either.right(new AddressDTO(addressDataSource.save(addressEntity)));
    }

    @Override
    public Either<RuntimeException, AddressDTO> enable(UUID id) {
        final AddressEntity addressEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                AddressDTO::toEntity);
        addressEntity.setStatus(true);
        return Either.right(new AddressDTO(addressDataSource.save(addressEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(addressDataSource.count());
    }

}
