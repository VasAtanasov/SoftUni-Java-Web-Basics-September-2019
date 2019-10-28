package org.atanasov.judge.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "problems")
@NamedQueries({
        @NamedQuery(
                name = "Problem.findWithUserSubmission",
                query = "SELECT DISTINCT(p) FROM Problem p LEFT JOIN FETCH p.submissions s WHERE s.user.id = :user_id"
        ),
        @NamedQuery(
                name = "Problem.findByIdWithUserSubmission",
                query = "SELECT p FROM Problem p LEFT JOIN FETCH p.submissions s WHERE p.id = :problem_id"
        )
})
public class Problem extends BaseEntity {

    public static final String FIND_WITH_USER_SUBMISSIONS = "Problem.findWithUserSubmission";
    public static final String FIND_BY_ID_WITH_USER_SUBMISSIONS = "Problem.findByIdWithUserSubmission";

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "points", columnDefinition = "INT DEFAULT 0")
    private Integer points;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Submission> submissions;
}
