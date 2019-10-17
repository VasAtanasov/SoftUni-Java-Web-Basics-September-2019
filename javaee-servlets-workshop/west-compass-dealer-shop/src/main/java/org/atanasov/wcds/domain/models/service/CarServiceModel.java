package org.atanasov.wcds.domain.models.service;

import lombok.*;
import org.atanasov.wcds.domain.enums.Engine;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CarServiceModel {
    private String brand;
    private String model;
    private Integer year;
    private Engine engine;
    private String userUsername;
}
