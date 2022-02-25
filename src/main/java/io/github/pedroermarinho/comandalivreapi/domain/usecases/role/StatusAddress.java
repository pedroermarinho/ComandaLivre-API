package io.github.pedroermarinho.comandalivreapi.domain.usecases.role;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.RoleDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.RoleRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class StatusAddress {

    private final RoleRepository roleRepository;

    public StatusAddress(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public RoleDTO disableRole(UUID id) {
        final List<Validation<UUID>> validations = Arrays.asList(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return roleRepository.disable(id);
    }

    @Transactional
    public RoleDTO enableRole(UUID id) {
        final List<Validation<UUID>> validations = Arrays.asList(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return roleRepository.enable(id);
    }
}
