package net.ln.exambackend.services.impl;

import net.ln.exambackend.dtos.ClientDTO;
import net.ln.exambackend.dtos.CreditDTO;
import net.ln.exambackend.dtos.RemboursementDTO;
import net.ln.exambackend.entities.Client;
import net.ln.exambackend.entities.Credit;
import net.ln.exambackend.entities.Remboursement;
import net.ln.exambackend.mappers.ClientMapper;
import net.ln.exambackend.mappers.CreditMapper;
import net.ln.exambackend.mappers.RemboursementMapper;
import net.ln.exambackend.repositories.ClientRepository;
import net.ln.exambackend.repositories.CreditRepository;
import net.ln.exambackend.repositories.RemboursementRepository;
import net.ln.exambackend.services.CreditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {

    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;
    private final RemboursementRepository remboursementRepository;
    private final ClientMapper clientMapper;
    private final CreditMapper creditMapper;
    private final RemboursementMapper remboursementMapper;

    public CreditServiceImpl(ClientRepository clientRepository, CreditRepository creditRepository, RemboursementRepository remboursementRepository, ClientMapper clientMapper, CreditMapper creditMapper, RemboursementMapper remboursementMapper) {
        this.clientRepository = clientRepository;
        this.creditRepository = creditRepository;
        this.remboursementRepository = remboursementRepository;
        this.clientMapper = clientMapper;
        this.creditMapper = creditMapper;
        this.remboursementMapper = remboursementMapper;
    }

    @Override
    public List<ClientDTO> listClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(clientMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        return clientMapper.toDTO(client);
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDTO(savedClient);
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        Client existingClient = clientRepository.findById(clientDTO.getId()).orElseThrow(() -> new RuntimeException("Client not found"));
        existingClient.setNom(clientDTO.getNom());
        existingClient.setEmail(clientDTO.getEmail());
        Client updatedClient = clientRepository.save(existingClient);
        return clientMapper.toDTO(updatedClient);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public List<CreditDTO> listCredits() {
        List<Credit> credits = creditRepository.findAll();
        return credits.stream().map(creditMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CreditDTO getCredit(Long creditId) {
        Credit credit = creditRepository.findById(creditId).orElseThrow(() -> new RuntimeException("Credit not found"));
        return creditMapper.toDTO(credit);
    }

    @Override
    public List<CreditDTO> getCreditsByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        return client.getCredits().stream().map(creditMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CreditDTO saveCredit(CreditDTO creditDTO) {
        Client client = clientRepository.findById(creditDTO.getClientId()).orElseThrow(() -> new RuntimeException("Client not found"));
        Credit credit = creditMapper.toEntity(creditDTO);
        credit.setClient(client);
        Credit savedCredit = creditRepository.save(credit);
        return creditMapper.toDTO(savedCredit);
    }

    @Override
    public CreditDTO updateCredit(CreditDTO creditDTO) {
        Credit existingCredit = creditRepository.findById(creditDTO.getId()).orElseThrow(() -> new RuntimeException("Credit not found"));
        Client client = clientRepository.findById(creditDTO.getClientId()).orElseThrow(() -> new RuntimeException("Client not found"));

        // Update common fields
        existingCredit.setDateDemande(creditDTO.getDateDemande());
        existingCredit.setStatut(creditDTO.getStatut());
        existingCredit.setDateAcceptation(creditDTO.getDateAcceptation());
        existingCredit.setMontant(creditDTO.getMontant());
        existingCredit.setDureeRemboursement(creditDTO.getDureeRemboursement());
        existingCredit.setTauxInteret(creditDTO.getTauxInteret());
        existingCredit.setClient(client);

        // MapStruct handles specific fields for subclasses during toEntity, but here we need to update existing entity
        // This part might need more specific handling depending on how you want to update subclass-specific fields.
        // For now, we rely on the fact that the DTO type will match the entity type and MapStruct will handle it.
        Credit updatedCredit = creditRepository.save(existingCredit);
        return creditMapper.toDTO(updatedCredit);
    }

    @Override
    public void deleteCredit(Long creditId) {
        creditRepository.deleteById(creditId);
    }

    @Override
    public List<RemboursementDTO> listRemboursementsByCreditId(Long creditId) {
        Credit credit = creditRepository.findById(creditId).orElseThrow(() -> new RuntimeException("Credit not found"));
        return credit.getRemboursements().stream().map(remboursementMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public RemboursementDTO getRemboursement(Long remboursementId) {
        Remboursement remboursement = remboursementRepository.findById(remboursementId).orElseThrow(() -> new RuntimeException("Remboursement not found"));
        return remboursementMapper.toDTO(remboursement);
    }

    @Override
    public RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO) {
        Credit credit = creditRepository.findById(remboursementDTO.getCreditId()).orElseThrow(() -> new RuntimeException("Credit not found"));
        Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
        remboursement.setCredit(credit);
        Remboursement savedRemboursement = remboursementRepository.save(remboursement);
        return remboursementMapper.toDTO(savedRemboursement);
    }

    @Override
    public RemboursementDTO updateRemboursement(RemboursementDTO remboursementDTO) {
        Remboursement existingRemboursement = remboursementRepository.findById(remboursementDTO.getId()).orElseThrow(() -> new RuntimeException("Remboursement not found"));
        Credit credit = creditRepository.findById(remboursementDTO.getCreditId()).orElseThrow(() -> new RuntimeException("Credit not found"));

        existingRemboursement.setDateRemboursement(remboursementDTO.getDateRemboursement());
        existingRemboursement.setMontant(remboursementDTO.getMontant());
        existingRemboursement.setTypeRemboursement(remboursementDTO.getTypeRemboursement());
        existingRemboursement.setCredit(credit);

        Remboursement updatedRemboursement = remboursementRepository.save(existingRemboursement);
        return remboursementMapper.toDTO(updatedRemboursement);
    }

    @Override
    public void deleteRemboursement(Long remboursementId) {
        remboursementRepository.deleteById(remboursementId);
    }
}
