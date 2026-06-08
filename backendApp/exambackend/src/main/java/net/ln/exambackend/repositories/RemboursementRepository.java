package net.ln.exambackend.repositories;

import net.ln.exambackend.entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
}
