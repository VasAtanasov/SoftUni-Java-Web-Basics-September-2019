package org.atanasov.judge.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.judge.util.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "submissions")
public class Submission extends BaseEntity {

    @ElementCollection
    @CollectionTable(
            name = "submission_code",
            joinColumns = @JoinColumn(name = "submission_id")
    )
    @Column(name = "code", nullable = false, columnDefinition = "TEXT")
    private List<String> code;

    @Column(name = "achieved_result", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer achievedResult;

    @Column(name = "created_on", nullable = false)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime createdOn;

    @ManyToOne(targetEntity = Problem.class)
    @JoinColumn(
            name = "problem_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Problem problem;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;

    @PrePersist
    public void onPrePersist() {
        createdOn = LocalDateTime.now();
    }

}
