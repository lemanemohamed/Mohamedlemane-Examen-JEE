package net.ln.exambackend.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ln.exambackend.entities.TypeBienFinance;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreditImmobilierDTO extends CreditDTO {
    private TypeBienFinance typeBienFinance;
}
