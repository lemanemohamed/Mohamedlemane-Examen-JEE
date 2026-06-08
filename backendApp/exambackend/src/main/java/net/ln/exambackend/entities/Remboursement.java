package net.ln.exambackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Remboursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateRemboursement;
    private double montant;
    @Enumerated(EnumType.STRING)
    private TypeRemboursement typeRemboursement;

    @ManyToOne
    private Credit credit;
}
