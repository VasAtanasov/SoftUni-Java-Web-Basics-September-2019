package org.atanasov.judge.domain.models.view;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemDetailsViewModel {
    private String id;
    private String name;
    private Integer points;
    private List<ProblemSubmissionListViewModel> submissions;
    private Double successRate;
}
