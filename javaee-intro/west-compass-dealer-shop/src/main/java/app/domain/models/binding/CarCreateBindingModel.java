package app.domain.models.binding;

import app.domain.enums.Engine;
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
    private Engine engine;
}
