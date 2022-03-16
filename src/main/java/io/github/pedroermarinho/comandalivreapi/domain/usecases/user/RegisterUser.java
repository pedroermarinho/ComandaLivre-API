package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.EmailValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class RegisterUser {

    private final UserRepository userRepository;

    public RegisterUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO execute(UserDTO userRegister) {
        final List<Validation<String>> validations = Arrays.asList(new NotNullValidation<>(), new EmailValidation());

        validations.forEach(validation -> validation.validationThrow(userRegister.email()));

        return userRepository.create(userRegister);
    }

}
