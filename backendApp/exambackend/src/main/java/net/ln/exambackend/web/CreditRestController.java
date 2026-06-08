package net.ln.exambackend.web;

import net.ln.exambackend.dtos.CreditDTO;
import net.ln.exambackend.dtos.CreditImmobilierDTO;
import net.ln.exambackend.dtos.CreditPersonnelDTO;
import net.ln.exambackend.dtos.CreditProfessionnelDTO;
import net.ln.exambackend.services.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@CrossOrigin(origins = "http://localhost:4200")
public class CreditRestController {

    private final CreditService creditService;

    public CreditRestController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public List<CreditDTO> getAllCredits() {
        return creditService.listCredits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable Long id) {
        try {
            CreditDTO creditDTO = creditService.getCredit(id);
            return ResponseEntity.ok(creditDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/client/{clientId}")
    public List<CreditDTO> getCreditsByClientId(@PathVariable Long clientId) {
        return creditService.getCreditsByClientId(clientId);
    }

    @PostMapping("/personnel")
    public ResponseEntity<CreditPersonnelDTO> createCreditPersonnel(@RequestBody CreditPersonnelDTO creditPersonnelDTO) {
        CreditPersonnelDTO savedCredit = (CreditPersonnelDTO) creditService.saveCredit(creditPersonnelDTO);
        return new ResponseEntity<>(savedCredit, HttpStatus.CREATED);
    }

    @PostMapping("/immobilier")
    public ResponseEntity<CreditImmobilierDTO> createCreditImmobilier(@RequestBody CreditImmobilierDTO creditImmobilierDTO) {
        CreditImmobilierDTO savedCredit = (CreditImmobilierDTO) creditService.saveCredit(creditImmobilierDTO);
        return new ResponseEntity<>(savedCredit, HttpStatus.CREATED);
    }

    @PostMapping("/professionnel")
    public ResponseEntity<CreditProfessionnelDTO> createCreditProfessionnel(@RequestBody CreditProfessionnelDTO creditProfessionnelDTO) {
        CreditProfessionnelDTO savedCredit = (CreditProfessionnelDTO) creditService.saveCredit(creditProfessionnelDTO);
        return new ResponseEntity<>(savedCredit, HttpStatus.CREATED);
    }

    @PutMapping("/personnel/{id}")
    public ResponseEntity<CreditPersonnelDTO> updateCreditPersonnel(@PathVariable Long id, @RequestBody CreditPersonnelDTO creditPersonnelDTO) {
        creditPersonnelDTO.setId(id);
        try {
            CreditPersonnelDTO updatedCredit = (CreditPersonnelDTO) creditService.updateCredit(creditPersonnelDTO);
            return ResponseEntity.ok(updatedCredit);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/immobilier/{id}")
    public ResponseEntity<CreditImmobilierDTO> updateCreditImmobilier(@PathVariable Long id, @RequestBody CreditImmobilierDTO creditImmobilierDTO) {
        creditImmobilierDTO.setId(id);
        try {
            CreditImmobilierDTO updatedCredit = (CreditImmobilierDTO) creditService.updateCredit(creditImmobilierDTO);
            return ResponseEntity.ok(updatedCredit);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/professionnel/{id}")
    public ResponseEntity<CreditProfessionnelDTO> updateCreditProfessionnel(@PathVariable Long id, @RequestBody CreditProfessionnelDTO creditProfessionnelDTO) {
        creditProfessionnelDTO.setId(id);
        try {
            CreditProfessionnelDTO updatedCredit = (CreditProfessionnelDTO) creditService.updateCredit(creditProfessionnelDTO);
            return ResponseEntity.ok(updatedCredit);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        try {
            creditService.deleteCredit(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
