package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StatusUser {

    private final UserRepository userRepository;

    public StatusUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO disableUser(UUID id) {
        final List<Validation<UUID>> validations = List.of(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return userRepository.disable(id);
    }

    @Transactional
    public UserDTO enableUser(UUID id) {
        final List<Validation<UUID>> validations = List.of(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return userRepository.enable(id);
    }
}
