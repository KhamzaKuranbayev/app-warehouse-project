package uz.pdp.appwarehouseproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    // DTO - Data Transfer Object
    private String firstname;
    private String lastname;
    private String email;
    private Timestamp createdAt;
    private Integer addressId;
    private List<Integer> roleIdList;

}
