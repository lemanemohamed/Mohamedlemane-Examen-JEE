package net.ln.exambackend.mappers;

import net.ln.exambackend.dtos.RemboursementDTO;
import net.ln.exambackend.entities.Remboursement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RemboursementMapper {
    RemboursementMapper INSTANCE = Mappers.getMapper(RemboursementMapper.class);

    @Mapping(target = "creditId", source = "credit.id")
    RemboursementDTO toDTO(Remboursement remboursement);

    @Mapping(target = "credit", ignore = true) // Credit will be set by service layer
    Remboursement toEntity(RemboursementDTO remboursementDTO);
}
