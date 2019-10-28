package org.atanasov.judge.domain.models.service;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemCreateServiceModel {

    @NotNull
    private String name;

    @NotNull
    @Min(value = 50)
    @Max(value = 250)
    private Integer points;
}
