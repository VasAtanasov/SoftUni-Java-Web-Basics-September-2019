package app.domain.models.view;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CarDetailsViewModel {
    private String brand;
    private String model;
    private Integer year;
    private String engine;
}
