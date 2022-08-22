package io.github.pedroermarinho.comandalivreapi.domain.usecases.organization;

import io.github.pedroermarinho.comandalivreapi.domain.record.OrganizationRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.OrganizationRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdateOrganization {

    private final OrganizationRepository organizationRepository;

    public UpdateOrganization(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public OrganizationRecord execute(UUID id, OrganizationRecord organizationParam) {
        UtilValidation.idNotNullValidationThrow(id);
        UtilValidation.objectNotNullValidationThrow(organizationParam);
        return organizationRepository.update(id, organizationParam).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }
}
