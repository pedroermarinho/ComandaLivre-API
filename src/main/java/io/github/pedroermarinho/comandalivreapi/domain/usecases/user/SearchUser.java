package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.EmailValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UsernameValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class SearchUser {

    private final UserRepository userRepository;

    public SearchUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO searchUserById(UUID id) {
        final List<Validation<UUID>> validations = List.of(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return userRepository.findById(id);
    }

    public UserDTO searchUserByEmail(String email) {

        final List<Validation<String>> validations = Arrays.asList(new NotNullValidation<>(), new EmailValidation());

        validations.forEach(validation -> validation.validationThrow(email));

        return userRepository.findByEmail(email);
    }

    public UserDTO searchUserByUsername(String username) {

        final List<Validation<String>> validations = Arrays.asList(new NotNullValidation<>(), new UsernameValidation());

        validations.forEach(validation -> validation.validationThrow(username));

        return userRepository.findByUsername(username);
    }

    public List<UserDTO> searchUserAll() {
        return userRepository.findAll();
    }

    public boolean existsByEmail(String email) {

        final List<Validation<String>> validations = Arrays.asList(new NotNullValidation<>(), new EmailValidation());

        validations.forEach(validation -> validation.validationThrow(email));

        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {

        final List<Validation<String>> validations = Arrays.asList(new NotNullValidation<>(), new UsernameValidation());

        validations.forEach(validation -> validation.validationThrow(username));

        return userRepository.existsByUsername(username);
    }

}
