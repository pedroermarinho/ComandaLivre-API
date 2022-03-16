package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.AddressEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.AddressRepository;
import io.github.pedroermarinho.comandalivreapi.infra.convert.AddressConvert;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.AddressDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AddressRepositoryImpl implements AddressRepository {

    private final AddressDataSource addressDataSource;
    private final AddressConvert convert;

    public AddressRepositoryImpl(AddressDataSource addressDataSource, AddressConvert convert) {
        this.addressDataSource = addressDataSource;
        this.convert = convert;
    }

    @Override
    public List<AddressDTO> findAll() {
        return addressDataSource.findAll().stream().map(AddressDTO::new).toList();
    }

    @Override
    public AddressDTO findById(UUID id) {
        return new AddressDTO(addressDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Endereço não encontrado! Id: " + id + ", Tipo: " + AddressDTO.class.getName())));
    }

    @Override
    public AddressDTO create(AddressDTO param) {
        return new AddressDTO(addressDataSource.save(param.toEntity()));
    }

    @Override
    public AddressDTO update(UUID id, AddressDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public AddressDTO disable(UUID id) {
        final AddressEntity addressEntity = findById(id).toEntity();
        addressEntity.setStatus(false);
        return new AddressDTO(addressDataSource.save(addressEntity));
    }

    @Override
    public AddressDTO enable(UUID id) {
        final AddressEntity addressEntity = findById(id).toEntity();
        addressEntity.setStatus(true);
        return new AddressDTO(addressDataSource.save(addressEntity));
    }

}
