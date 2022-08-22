package io.github.pedroermarinho.comandalivreapi.domain.usecases.role;

import io.github.pedroermarinho.comandalivreapi.domain.record.RoleRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.RoleRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegisterRole {

    private final RoleRepository roleRepository;

    public RegisterRole(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public RoleRecord execute(RoleRecord roleRegister) {
        UtilValidation.objectNotNullValidationThrow(roleRegister);

        final List<Validation<String>> validations = List.of(new NotNullValidation<>());
        validations.forEach(validation -> validation.validationThrow(roleRegister.name()));

        return roleRepository.create(roleRegister).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

}
