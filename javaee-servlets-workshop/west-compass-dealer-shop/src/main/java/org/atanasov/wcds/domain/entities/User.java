package org.atanasov.wcds.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@NamedQueries({
        @NamedQuery(
                name = "User.findByUsername",
                query = "SELECT u FROM User u WHERE u.username = :username"
        ),
        @NamedQuery(
                name = "User.findByUsernameAndPassword",
                query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"
        ),
        @NamedQuery(
                name = "User.findByEmail",
                query = "SELECT u FROM User u WHERE u.email = :email"
        ),
})
public class User extends BaseEntity {
    public static final String FIND_BY_USERNAME = "User.findByUsername";
    public static final String FIND_BY_USERNAME_AND_PASSWORD = "User.findByUsernameAndPassword";
    public static final String FIND_BY_EMAIL = "User.findByEmail";

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @OneToMany(
            targetEntity = Car.class,
            mappedBy = "user",
            fetch = FetchType.EAGER
    )
    private List<Car> cars;
}
