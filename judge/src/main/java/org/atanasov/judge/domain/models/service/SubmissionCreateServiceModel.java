package org.atanasov.judge.domain.models.service;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SubmissionCreateServiceModel {

    @NotNull
    private String problemId;

    @NotNull
    private String userId;

    @NotNull
    private String code;

    @NotNull
    @Min(value = 50)
    @Max(value = 250)
    private Integer problemPoints;

    @NotNull
    private Integer achievedResult;
}
