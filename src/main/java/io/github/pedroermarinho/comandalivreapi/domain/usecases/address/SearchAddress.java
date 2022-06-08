package io.github.pedroermarinho.comandalivreapi.domain.usecases.address;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.AddressRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchAddress {

    private final AddressRepository addressRepository;

    public SearchAddress(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressDTO searchAddressById(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return addressRepository.findById(id);
    }

    public List<AddressDTO> searchAddressAll() {
        return addressRepository.findAll();
    }

}
