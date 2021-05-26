package uz.pdp.appwarehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "category_name")
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private Category parent;      // parent_id
}
