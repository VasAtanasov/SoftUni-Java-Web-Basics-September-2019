package org.atanasov.wcds.domain.entities;

import lombok.*;
import org.atanasov.wcds.domain.enums.Engine;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "engine", nullable = false)
    @Convert(converter = Engine.EngineConverter.class)
    private Engine engine;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;
}
