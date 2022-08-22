package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import io.github.pedroermarinho.comandalivreapi.domain.record.UserRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchUser {

    private final UserRepository userRepository;

    public SearchUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRecord searchUserById(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return userRepository.findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

    public UserRecord searchUserByEmail(String email) {
        UtilValidation.emailNotNullValidationThrow(email);
        return userRepository.findByEmail(email).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

    public UserRecord searchUserByUsername(String username) {
        UtilValidation.usernameNotNullValidationThrow(username);
        return userRepository.findByUsername(username).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

    public List<UserRecord> searchUserAll() {
        return userRepository.findAll();
    }

    public boolean existsByEmail(String email) {
        UtilValidation.emailNotNullValidationThrow(email);
        return userRepository.existsByEmail(email).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

    public boolean existsByUsername(String username) {
        UtilValidation.usernameNotNullValidationThrow(username);
        return userRepository.existsByUsername(username).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

}
