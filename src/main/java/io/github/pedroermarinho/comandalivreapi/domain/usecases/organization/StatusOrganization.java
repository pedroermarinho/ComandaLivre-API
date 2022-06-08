package io.github.pedroermarinho.comandalivreapi.domain.usecases.organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.OrganizationRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class StatusOrganization {

    private final OrganizationRepository organizationRepository;

    public StatusOrganization(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public OrganizationDTO disableOrganization(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return organizationRepository.disable(id);
    }

    @Transactional
    public OrganizationDTO enableOrganization(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return organizationRepository.enable(id);
    }
}
