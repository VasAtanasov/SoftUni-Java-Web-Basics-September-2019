package app.domain.models.binding;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CarCreateBindingModel {
    private String brand;
    private String model;
    private Integer year;
    private String engine;
}
