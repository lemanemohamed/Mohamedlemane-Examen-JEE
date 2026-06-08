package net.ln.exambackend.web;

import net.ln.exambackend.dtos.RemboursementDTO;
import net.ln.exambackend.services.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
@CrossOrigin(origins = "http://localhost:4200")
public class RemboursementRestController {

    private final CreditService creditService;

    public RemboursementRestController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/credit/{creditId}")
    public List<RemboursementDTO> getRemboursementsByCreditId(@PathVariable Long creditId) {
        return creditService.listRemboursementsByCreditId(creditId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemboursementDTO> getRemboursementById(@PathVariable Long id) {
        try {
            RemboursementDTO remboursementDTO = creditService.getRemboursement(id);
            return ResponseEntity.ok(remboursementDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RemboursementDTO> createRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
        RemboursementDTO savedRemboursement = creditService.saveRemboursement(remboursementDTO);
        return new ResponseEntity<>(savedRemboursement, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemboursementDTO> updateRemboursement(@PathVariable Long id, @RequestBody RemboursementDTO remboursementDTO) {
        remboursementDTO.setId(id); // Ensure the ID from the path is used
        try {
            RemboursementDTO updatedRemboursement = creditService.updateRemboursement(remboursementDTO);
            return ResponseEntity.ok(updatedRemboursement);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        try {
            creditService.deleteRemboursement(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
