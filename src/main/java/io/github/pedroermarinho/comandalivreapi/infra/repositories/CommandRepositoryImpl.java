package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.CommandEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.CommandRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.CommandDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CommandRepositoryImpl implements CommandRepository {

    private final CommandDataSource commandDataSource;

    public CommandRepositoryImpl(CommandDataSource commandDataSource) {
        this.commandDataSource = commandDataSource;
    }

    @Override
    public List<CommandDTO> findAll() {
        return commandDataSource.findAll().stream().map(CommandDTO::new).toList();
    }

    @Override
    public CommandDTO findById(UUID id) {
        return new CommandDTO(commandDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Comanda não encontrado! Id: " + id + ", Tipo: " + CommandDTO.class.getName())));
    }

    @Override
    public CommandDTO create(CommandDTO param) {
        return new CommandDTO(commandDataSource.save(param.toEntity()));
    }

    @Override
    public CommandDTO update(UUID id, CommandDTO param) {
        final CommandEntity commandEntity = findById(id).toEntity();
        commandEntity.setIdentification(param.identification());
        return new CommandDTO(commandDataSource.save(commandEntity));
    }

    @Override
    public CommandDTO disable(UUID id) {
        final CommandEntity commandEntity = findById(id).toEntity();
        commandEntity.setStatus(false);
        return new CommandDTO(commandDataSource.save(commandEntity));
    }

    @Override
    public CommandDTO enable(UUID id) {
        final CommandEntity commandEntity = findById(id).toEntity();
        commandEntity.setStatus(true);
        return new CommandDTO(commandDataSource.save(commandEntity));
    }

    @Override
    public long count() {
        return commandDataSource.count();
    }

    @Override
    public CommandDTO updatePaidOff(UUID id, boolean paidOff) {
        final CommandEntity commandEntity = findById(id).toEntity();
        commandEntity.setPaidOff(paidOff);
        return new CommandDTO(commandDataSource.save(commandEntity));
    }
}
