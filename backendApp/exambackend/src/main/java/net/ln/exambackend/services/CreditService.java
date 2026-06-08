package net.ln.exambackend.services;

import net.ln.exambackend.dtos.ClientDTO;
import net.ln.exambackend.dtos.CreditDTO;
import net.ln.exambackend.dtos.RemboursementDTO;

import java.util.List;

public interface CreditService {
    // Client operations
    List<ClientDTO> listClients();
    ClientDTO getClient(Long clientId);
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO updateClient(ClientDTO clientDTO);
    void deleteClient(Long clientId);

    // Credit operations
    List<CreditDTO> listCredits();
    CreditDTO getCredit(Long creditId);
    List<CreditDTO> getCreditsByClientId(Long clientId);
    CreditDTO saveCredit(CreditDTO creditDTO);
    CreditDTO updateCredit(CreditDTO creditDTO);
    void deleteCredit(Long creditId);

    // Remboursement operations
    List<RemboursementDTO> listRemboursementsByCreditId(Long creditId);
    RemboursementDTO getRemboursement(Long remboursementId);
    RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO);
    RemboursementDTO updateRemboursement(RemboursementDTO remboursementDTO);
    void deleteRemboursement(Long remboursementId);
}
