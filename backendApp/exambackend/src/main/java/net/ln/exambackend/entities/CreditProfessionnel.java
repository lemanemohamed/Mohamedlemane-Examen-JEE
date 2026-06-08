package net.ln.exambackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("PROFESSIONNEL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditProfessionnel extends Credit {
    private String motif;
    private String raisonSocialeEntreprise;

    public <T> CreditProfessionnel(Object o, LocalDate localDate, StatutCredit statutCredit, LocalDate localDate1, double v, int i, double v1, Client client2, List<T> ts, String développementStartup, String s) {
    }

}
