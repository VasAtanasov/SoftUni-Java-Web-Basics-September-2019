package org.atanasov.sboj.domain.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.sboj.domain.enums.Sector;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationViewModel {
    private String id;
    private Sector sector;
    private String profession;
    private BigDecimal salary;
    private String description;
}
