package pl.coderslab.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

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

    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @ManyToMany(mappedBy = "users")
    private List<Task> tasks;

    public User (User user){
        this.password = user.getPassword();
        this.id = user.getId();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
    }
}
