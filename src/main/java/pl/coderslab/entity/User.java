package pl.coderslab.entity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String login;

    @Column(unique = true)
    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String username;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    private List<Project> project;

    @ManyToMany(mappedBy = "users")
    private List<Task> tasks;

    private boolean active;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @
    private Set<Role> role;

    public User (User user){
        this.password = user.getPassword();
        this.id = user.getId();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
