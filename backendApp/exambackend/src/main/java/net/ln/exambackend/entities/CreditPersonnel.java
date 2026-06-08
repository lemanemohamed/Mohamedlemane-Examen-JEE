package net.ln.exambackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("PERSONNEL")
@Data
@NoArgsConstructor @AllArgsConstructor
public class CreditPersonnel extends Credit {
    private String motif; // exemple achat de voiture, études, travaux

    public CreditPersonnel(Long id, LocalDate dateDemande, net.ln.exambackend.entities.StatutCredit statut, LocalDate dateEffet, Double montant, Integer dureeMois, Double tauxInteret, Client client, List<Remboursement> remboursements, String motif) {
        super(id, dateDemande, statut, dateEffet, montant, dureeMois, tauxInteret, client, remboursements);
        this.motif = motif;
    }
}
