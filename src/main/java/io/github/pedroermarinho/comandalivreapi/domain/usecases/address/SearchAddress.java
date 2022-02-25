package io.github.pedroermarinho.comandalivreapi.domain.usecases.address;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.AddressRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class SearchAddress {

    private final AddressRepository addressRepository;

    public SearchAddress(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressDTO searchAddressById(UUID id) {
        final List<Validation<UUID>> validations = Arrays.asList(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return addressRepository.findById(id);
    }

    public List<AddressDTO> searchAddressAll() {
        return addressRepository.findAll();
    }

}
