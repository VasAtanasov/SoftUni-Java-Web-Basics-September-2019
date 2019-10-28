package org.atanasov.judge.domain.models.view;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemHomeViewModel {
    private String id;
    private String name;
    private Integer completion;
}
