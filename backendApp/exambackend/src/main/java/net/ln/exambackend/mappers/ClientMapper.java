package net.ln.exambackend.mappers;

import net.ln.exambackend.dtos.ClientDTO;
import net.ln.exambackend.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CreditMapper.class})
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO toDTO(Client client);

    @Mapping(target = "credits", ignore = true)
    Client toEntity(ClientDTO clientDTO);
}
