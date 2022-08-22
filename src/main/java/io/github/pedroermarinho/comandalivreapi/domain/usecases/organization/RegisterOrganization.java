package io.github.pedroermarinho.comandalivreapi.domain.usecases.organization;

import io.github.pedroermarinho.comandalivreapi.domain.record.OrganizationRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.OrganizationRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegisterOrganization {

    private final OrganizationRepository organizationRepository;

    public RegisterOrganization(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public OrganizationRecord execute(OrganizationRecord organizationRegister) {
        UtilValidation.objectNotNullValidationThrow(organizationRegister);

        final List<Validation<String>> validations = List.of(new NotNullValidation<>());
        validations.forEach(validation -> validation.validationThrow(organizationRegister.name()));

        return organizationRepository.create(organizationRegister).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

}
