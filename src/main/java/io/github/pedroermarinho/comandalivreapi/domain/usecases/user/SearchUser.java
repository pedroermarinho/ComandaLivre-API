package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
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

    public UserDTO searchUserById(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return userRepository.findById(id);
    }

    public UserDTO searchUserByEmail(String email) {
        UtilValidation.emailNotNullValidationThrow(email);
        return userRepository.findByEmail(email);
    }

    public UserDTO searchUserByUsername(String username) {
        UtilValidation.usernameNotNullValidationThrow(username);
        return userRepository.findByUsername(username);
    }

    public List<UserDTO> searchUserAll() {
        return userRepository.findAll();
    }

    public boolean existsByEmail(String email) {
        UtilValidation.emailNotNullValidationThrow(email);
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        UtilValidation.usernameNotNullValidationThrow(username);
        return userRepository.existsByUsername(username);
    }

}
