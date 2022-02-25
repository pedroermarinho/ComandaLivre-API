package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
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
    private final AddressConvert convert = new AddressConvert();

    public AddressRepositoryImpl(AddressDataSource addressDataSource) {
        this.addressDataSource = addressDataSource;
    }

    @Override
    public List<AddressDTO> findAll() {
        return convert.formEntity(addressDataSource.findAll());
    }

    @Override
    public AddressDTO findById(UUID id) {
        return convert.formEntity(addressDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Endereço não encontrado! Id: " + id + ", Tipo: " + AddressDTO.class.getName())));
    }

    @Override
    public AddressDTO create(AddressDTO param) {
        return convert.formEntity(addressDataSource.save(convert.formDTO(param)));
    }

    @Override
    public AddressDTO update(UUID id, AddressDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public AddressDTO disable(UUID id) {
        final AddressDTO addressDTO = findById(id);
        addressDTO.setStatus(false);
        return convert.formEntity(addressDataSource.save(convert.formDTO(addressDTO)));
    }

    @Override
    public AddressDTO enable(UUID id) {
        final AddressDTO addressDTO = findById(id);
        addressDTO.setStatus(true);
        return convert.formEntity(addressDataSource.save(convert.formDTO(addressDTO)));
    }

}
