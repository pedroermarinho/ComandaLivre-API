package io.github.pedroermarinho.comandalivreapi.domain.usecases.role;

import io.github.pedroermarinho.comandalivreapi.domain.record.RoleRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.RoleRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdateRole {

    private final RoleRepository roleRepository;

    public UpdateRole(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public RoleRecord execute(UUID id, RoleRecord roleParam) {
        UtilValidation.idNotNullValidationThrow(id);
        UtilValidation.objectNotNullValidationThrow(roleParam);
        return roleRepository.update(id, roleParam).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }
}
