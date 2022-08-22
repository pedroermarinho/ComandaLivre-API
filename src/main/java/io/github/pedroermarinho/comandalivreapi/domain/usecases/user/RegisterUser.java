package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.record.UserRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterUser {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisterUser(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public UserRecord execute(UserRecord userRegister) {
        UtilValidation.objectNotNullValidationThrow(userRegister);
        UtilValidation.emailNotNullValidationThrow(userRegister.email());

        final UserEntity userEntity = userRegister.toEntity();
        userEntity.setPassword(bCryptPasswordEncoder.encode(userRegister.password()));


        return userRepository.create(new UserRecord(userEntity)).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

}
