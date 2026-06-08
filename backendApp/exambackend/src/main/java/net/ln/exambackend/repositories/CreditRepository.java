package net.ln.exambackend.repositories;

import net.ln.exambackend.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
