package io.github.pedroermarinho.comandalivreapi.domain.usecases.organization;

import io.github.pedroermarinho.comandalivreapi.domain.record.OrganizationRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.OrganizationRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchOrganization {

    private final OrganizationRepository organizationRepository;

    public SearchOrganization(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public OrganizationRecord searchOrganizationById(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return organizationRepository.findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }


    public List<OrganizationRecord> searchOrganizationAll() {
        return organizationRepository.findAll();
    }

}
