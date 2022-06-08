package io.github.pedroermarinho.comandalivreapi.domain.usecases.role;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.RoleDTO;
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

    public RoleDTO searchRoleById(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return roleRepository.findById(id);
    }

    public List<RoleDTO> searchRoleAll() {
        return roleRepository.findAll();
    }

}
