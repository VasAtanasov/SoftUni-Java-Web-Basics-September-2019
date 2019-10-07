package app.domain.models.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CarCreateServiceModel {
    private String brand;
    private String model;
    private Integer year;
    private String engine;
}
