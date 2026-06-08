package net.ln.exambackend.dtos;

import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String nom;
    private String email;
}
