package net.ln.exambackend.dtos;

import lombok.Data;
import net.ln.exambackend.entities.StatutCredit;

import java.time.LocalDate;
import java.util.List;

@Data
public abstract class CreditDTO {
    private Long id;
    private LocalDate dateDemande;
    private StatutCredit statut;
    private LocalDate dateAcceptation;
    private double montant;
    private int dureeRemboursement;
    private double tauxInteret;
    private Long clientId; // To link to the client
    private List<RemboursementDTO> remboursements;
}
