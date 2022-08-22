package io.github.pedroermarinho.comandalivreapi.domain.usecases.role;

import io.github.pedroermarinho.comandalivreapi.domain.record.RoleRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.RoleRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchRole {

    private final RoleRepository roleRepository;

    public SearchRole(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleRecord searchRoleById(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return roleRepository.findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

    public List<RoleRecord> searchRoleAll() {
        return roleRepository.findAll();
    }

}
