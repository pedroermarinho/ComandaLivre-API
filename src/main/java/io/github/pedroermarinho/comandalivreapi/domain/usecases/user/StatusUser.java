package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class StatusUser {

    private final UserRepository userRepository;

    public StatusUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO disableUser(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return userRepository.disable(id);
    }

    @Transactional
    public UserDTO enableUser(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return userRepository.enable(id);
    }
}
