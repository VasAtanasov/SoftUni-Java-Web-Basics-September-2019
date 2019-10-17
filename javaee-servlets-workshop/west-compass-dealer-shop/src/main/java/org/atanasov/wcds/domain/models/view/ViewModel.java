package org.atanasov.wcds.domain.models.view;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewModel<T> {
    T obj;
}
