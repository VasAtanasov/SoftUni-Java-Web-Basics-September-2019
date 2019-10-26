package org.atanasov.sboj.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.sboj.domain.enums.Sector;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "job_applications")
public class JobApplication extends BaseEntity {

    @Column(name = "sector", nullable = false)
    @Convert(converter = Sector.SectorConverter.class)
    private Sector sector;

    @Column(name = "profession", nullable = false)
    private String profession;

    @Column(name = "salary", nullable = false, columnDefinition = "DECIMAL(10, 2) DEFAULT '0.00'")
    private BigDecimal salary;

    @Column(name = "description", nullable = false)
    private String description;
}
