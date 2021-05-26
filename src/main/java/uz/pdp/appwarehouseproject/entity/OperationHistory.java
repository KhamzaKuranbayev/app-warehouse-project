package uz.pdp.appwarehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseproject.enums.ActionType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Timestamp date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActionType actionType;


}
