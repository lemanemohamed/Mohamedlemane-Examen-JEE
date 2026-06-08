package net.ln.exambackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("IMMOBILIER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditImmobilier extends Credit {
    @Enumerated(EnumType.STRING)
    private TypeBienFinance typeBienFinance; // Appartement, Maison, ou Local Commercial

    public <T> CreditImmobilier(Object o, LocalDate localDate, StatutCredit statutCredit, Object o1, double v, int i, double v1, Client client1, List<T> ts, TypeBienFinance typeBienFinance) {
    }

}
