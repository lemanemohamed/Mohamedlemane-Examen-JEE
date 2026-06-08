package net.ln.exambackend;

import net.ln.exambackend.entities.*;
import net.ln.exambackend.entities.StatutCredit;
import net.ln.exambackend.entities.TypeRemboursement;
import net.ln.exambackend.repositories.ClientRepository;
import net.ln.exambackend.repositories.CreditRepository;
import net.ln.exambackend.repositories.RemboursementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@Component
public class DataLoader implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;
    private final RemboursementRepository remboursementRepository;

    public DataLoader(ClientRepository clientRepository, CreditRepository creditRepository, RemboursementRepository remboursementRepository) {
        this.clientRepository = clientRepository;
        this.creditRepository = creditRepository;
        this.remboursementRepository = remboursementRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data for fresh start (optional, for testing)
        remboursementRepository.deleteAll();
        creditRepository.deleteAll();
        clientRepository.deleteAll();

        // Create Clients
        Client client1 = new Client(null, "Alice Smith", "alice.smith@example.com", Collections.emptyList());
        Client client2 = new Client(null, "Bob Johnson", "bob.j@example.com", Collections.emptyList());
        clientRepository.saveAll(Arrays.asList(client1, client2));

        // Create Credits for Client 1
        CreditPersonnel creditPersonnel1 = new CreditPersonnel(null, LocalDate.now(), StatutCredit.ACCEPTE, LocalDate.now().plusDays(5), 15000.0, 36, 0.05, client1, Collections.emptyList(), "Achat de voiture");
        CreditImmobilier creditImmobilier1 = new CreditImmobilier(null, LocalDate.now().minusMonths(2), StatutCredit.EN_COURS, null, 250000.0, 240, 0.03, client1, Collections.emptyList(), TypeBienFinance.APPARTEMENT);
        creditRepository.saveAll(Arrays.asList(creditPersonnel1, creditImmobilier1));

        // Create Credits for Client 2
        CreditProfessionnel creditProfessionnel2 = new CreditProfessionnel(null, LocalDate.now().minusWeeks(1), StatutCredit.ACCEPTE, LocalDate.now().plusDays(2), 50000.0, 60, 0.04, client2, Collections.emptyList(), "Développement startup", "Tech Solutions Inc.");
        creditRepository.save(creditProfessionnel2);

        // Create Remboursements for creditPersonnel1
        Remboursement remb1_1 = new Remboursement(null, LocalDate.now().plusMonths(1), 450.0, TypeRemboursement.MENSUALITE, creditPersonnel1);
        Remboursement remb1_2 = new Remboursement(null, LocalDate.now().plusMonths(2), 450.0, TypeRemboursement.MENSUALITE, creditPersonnel1);
        remboursementRepository.saveAll(Arrays.asList(remb1_1, remb1_2));

        // Create Remboursements for creditProfessionnel2
        Remboursement remb2_1 = new Remboursement(null, LocalDate.now().plusMonths(1), 1000.0, TypeRemboursement.MENSUALITE, creditProfessionnel2);
        Remboursement remb2_2 = new Remboursement(null, LocalDate.now().plusMonths(3), 5000.0, TypeRemboursement.REMBOURSEMENT_ANTCIPE, creditProfessionnel2);
        remboursementRepository.saveAll(Arrays.asList(remb2_1, remb2_2));

        System.out.println("Database initialized with test data.");
    }
}
