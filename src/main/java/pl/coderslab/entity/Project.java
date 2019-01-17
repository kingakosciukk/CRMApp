package pl.coderslab.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Pattern(regexp = "(http|https)://.+\\.[a-z]{2,3}")
    private String website;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date created;

    private boolean active;

    @ManyToMany
    private List <User> users;
}
