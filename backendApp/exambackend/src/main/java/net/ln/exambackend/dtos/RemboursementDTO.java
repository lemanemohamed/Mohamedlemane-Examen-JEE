package net.ln.exambackend.dtos;

import lombok.Data;
import net.ln.exambackend.entities.TypeRemboursement;

import java.time.LocalDate;

@Data
public class RemboursementDTO {
    private Long id;
    private LocalDate dateRemboursement;
    private double montant;
    private TypeRemboursement typeRemboursement;
    private Long creditId; // To link to the credit
}
