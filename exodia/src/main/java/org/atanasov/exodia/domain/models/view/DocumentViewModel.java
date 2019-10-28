package org.atanasov.exodia.domain.models.view;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentViewModel {
    private String id;
    private String title;
    private String content;
}
