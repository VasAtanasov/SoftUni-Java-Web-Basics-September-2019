package org.atanasov.casebook.domain.entities;

import lombok.*;
import org.atanasov.casebook.domain.enums.Gender;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
                name = "User.findByGender",
                query = "SELECT u FROM User u WHERE u.gender = :gender"
        ),
        @NamedQuery(
                name = "User.findAllOtherUsers",
                query = "SELECT DISTINCT u FROM User u WHERE u.id != :id AND :user NOT MEMBER OF u.friends"
        ),
})
public class User extends BaseEntity {

    public static final String FIND_BY_USERNAME = "User.findByUsername";
    public static final String FIND_BY_USERNAME_AND_PASSWORD = "User.findByUsernameAndPassword";
    public static final String FIND_BY_GENDER = "User.findByGender";
    public static final String FIND_OTHER_USERS = "User.findAllOtherUsers";

    @Column(name = "username", unique = true, nullable = false, updatable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender", nullable = false)
    @Convert(converter = Gender.GenderConverter.class)
    private Gender gender;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_friends",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "friend_id",
                    referencedColumnName = "id"
            )
    )
    private List<User> friends;

    @Transient
    public void addFriend(User user) {
        friends.add(user);
        user.friends.add(this);
    }

    @Transient
    public void removeFriend(User user) {
        friends.remove(user);
        user.friends.remove(this);
    }
}
