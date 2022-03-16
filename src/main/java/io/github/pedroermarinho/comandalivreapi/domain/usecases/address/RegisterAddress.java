package io.github.pedroermarinho.comandalivreapi.domain.usecases.address;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.AddressRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class RegisterAddress {

    private final AddressRepository addressRepository;

    public RegisterAddress(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public AddressDTO execute(AddressDTO addressRegister) {
        final List<Validation<String>> validations = Arrays.asList(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(addressRegister.cep()));

        return addressRepository.create(addressRegister);
    }

}
