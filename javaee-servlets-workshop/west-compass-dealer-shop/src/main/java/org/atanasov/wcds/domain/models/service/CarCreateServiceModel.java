package org.atanasov.wcds.domain.models.service;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CarCreateServiceModel {

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private Integer year;

    @NotNull
    private String engine;

    @NotNull
    private String user;
}
