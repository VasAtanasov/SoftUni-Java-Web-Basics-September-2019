package org.atanasov.sboj.domain.models.service;

import lombok.*;
import org.atanasov.sboj.domain.enums.Sector;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JobAddServiceModel {

    @NotNull
    private Sector sector;

    @NotNull
    @Size(min = 1, max = 32)
    private String profession;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal salary;

    private String description;
}
