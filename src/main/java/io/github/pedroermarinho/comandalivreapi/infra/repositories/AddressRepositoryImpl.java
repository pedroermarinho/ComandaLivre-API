package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.entities.AddressEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.record.AddressRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.AddressRepository;
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
    public List<AddressRecord> findAll() {
        return addressDataSource.findAll().stream().map(AddressRecord::new).toList();
    }

    @Override
    public Either<RuntimeException, AddressRecord> findById(UUID id) {
        return addressDataSource.findById(id)
                .<Either<RuntimeException, AddressRecord>>map(entity -> Either.right(new AddressRecord(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Endereço não encontrado! Id: " + id + ", Tipo: " + AddressRecord.class.getName())));
    }

    @Override
    public Either<RuntimeException, AddressRecord> create(AddressRecord param) {
        return Either.right(new AddressRecord(addressDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, AddressRecord> update(UUID id, AddressRecord param) {
        throw new NotImplementedException();
    }

    @Override
    public Either<RuntimeException, AddressRecord> disable(UUID id) {
        final AddressEntity addressEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                AddressRecord::toEntity);
        addressEntity.setStatus(false);
        return Either.right(new AddressRecord(addressDataSource.save(addressEntity)));
    }

    @Override
    public Either<RuntimeException, AddressRecord> enable(UUID id) {
        final AddressEntity addressEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                AddressRecord::toEntity);
        addressEntity.setStatus(true);
        return Either.right(new AddressRecord(addressDataSource.save(addressEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(addressDataSource.count());
    }

}
