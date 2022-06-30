package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterUser {

    private final UserRepository userRepository;

    public RegisterUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO execute(UserDTO userRegister) {
        UtilValidation.objectNotNullValidationThrow(userRegister);
        UtilValidation.emailNotNullValidationThrow(userRegister.email());

        return userRepository.create(userRegister).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

}
