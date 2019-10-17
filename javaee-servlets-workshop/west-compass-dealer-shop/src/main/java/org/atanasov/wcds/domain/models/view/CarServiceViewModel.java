package org.atanasov.wcds.domain.models.view;

import lombok.*;
import org.atanasov.wcds.domain.enums.Engine;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CarServiceViewModel {
    private String brand;
    private String model;
    private Integer year;
    private Engine engine;
    private String userUsername;
}
