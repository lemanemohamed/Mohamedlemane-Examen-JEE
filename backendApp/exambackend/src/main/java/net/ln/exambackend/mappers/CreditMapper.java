package net.ln.exambackend.mappers;

import net.ln.exambackend.dtos.CreditDTO;
import net.ln.exambackend.dtos.CreditImmobilierDTO;
import net.ln.exambackend.dtos.CreditPersonnelDTO;
import net.ln.exambackend.dtos.CreditProfessionnelDTO;
import net.ln.exambackend.entities.Credit;
import net.ln.exambackend.entities.CreditImmobilier;
import net.ln.exambackend.entities.CreditPersonnel;
import net.ln.exambackend.entities.CreditProfessionnel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;
import org.mapstruct.SubclassMappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {RemboursementMapper.class})
public abstract class CreditMapper { // Made abstract to allow for custom mapping logic if needed
    public static final CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "remboursements", source = "remboursements")
    @SubclassMappings({
            @SubclassMapping(source = CreditPersonnel.class, target = CreditPersonnelDTO.class),
            @SubclassMapping(source = CreditImmobilier.class, target = CreditImmobilierDTO.class),
            @SubclassMapping(source = CreditProfessionnel.class, target = CreditProfessionnelDTO.class)
    })
    public abstract CreditDTO toDTO(Credit credit);

    @Mapping(target = "client", ignore = true) // Client will be set by service layer
    @Mapping(target = "remboursements", ignore = true) // Remboursements will be handled separately
    @SubclassMappings({
            @SubclassMapping(source = CreditPersonnelDTO.class, target = CreditPersonnel.class),
            @SubclassMapping(source = CreditImmobilierDTO.class, target = CreditImmobilier.class),
            @SubclassMapping(source = CreditProfessionnelDTO.class, target = CreditProfessionnel.class)
    })
    public abstract Credit toEntity(CreditDTO creditDTO);
}
