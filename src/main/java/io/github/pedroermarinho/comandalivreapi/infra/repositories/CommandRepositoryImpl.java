package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.CommandRepository;
import io.github.pedroermarinho.comandalivreapi.infra.convert.CommandConvert;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.CommandDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CommandRepositoryImpl implements CommandRepository {

    private final CommandDataSource commandDataSource;
    private final CommandConvert convert = new CommandConvert();

    public CommandRepositoryImpl(CommandDataSource commandDataSource) {
        this.commandDataSource = commandDataSource;
    }

    @Override
    public List<CommandDTO> findAll() {
        return convert.formEntity(commandDataSource.findAll());
    }

    @Override
    public CommandDTO findById(UUID id) {
        return convert.formEntity(commandDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Comanda não encontrado! Id: " + id + ", Tipo: " + CommandDTO.class.getName())));
    }

    @Override
    public CommandDTO create(CommandDTO param) {
        return convert.formEntity(commandDataSource.save(convert.formDTO(param)));
    }

    @Override
    public CommandDTO update(UUID id, CommandDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public CommandDTO disable(UUID id) {
        final CommandDTO commandDTO = findById(id);
        commandDTO.setStatus(false);
        return convert.formEntity(commandDataSource.save(convert.formDTO(commandDTO)));
    }

    @Override
    public CommandDTO enable(UUID id) {
        final CommandDTO commandDTO = findById(id);
        commandDTO.setStatus(true);
        return convert.formEntity(commandDataSource.save(convert.formDTO(commandDTO)));
    }

}
