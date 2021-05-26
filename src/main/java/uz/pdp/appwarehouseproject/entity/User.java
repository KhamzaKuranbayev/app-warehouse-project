package uz.pdp.appwarehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {

    /*static final String ketmon = "user_id_sec";
    @Id
    @SequenceGenerator(name = ketmon, sequenceName = ketmon, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ketmon)
    private Integer id;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true, length = 15)
    @Length(min = 3, max = 10)
    private String email;

    @Column(nullable = false)
    private Timestamp createdAt;

    @OneToOne
    private Address address;        // address_id  int

    @ManyToMany
    private List<Role> role;

}
