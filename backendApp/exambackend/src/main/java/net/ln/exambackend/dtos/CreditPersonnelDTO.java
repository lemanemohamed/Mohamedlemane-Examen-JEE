package net.ln.exambackend.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreditPersonnelDTO extends CreditDTO {
    private String motif;
}
