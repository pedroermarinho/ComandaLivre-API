package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.EmailValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UsernameValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UpdateUser {

    private final UserRepository userRepository;

    public UpdateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO execute(UUID id, UserDTO userParam) {

        final List<Validation<UUID>> idValidations = Arrays.asList(new NotNullValidation<>());
        final List<Validation<String>> emailValidations = Arrays.asList(new NotNullValidation<>(), new EmailValidation());
        final List<Validation<String>> usernameValidations = Arrays.asList(new NotNullValidation<>(), new UsernameValidation());

        idValidations.forEach(validation -> validation.validationThrow(id));
        emailValidations.forEach(validation -> validation.validationThrow(userParam.email()));
        usernameValidations.forEach(validation -> validation.validationThrow(userParam.username()));

        return userRepository.update(id, userParam);
    }
}
