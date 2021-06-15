package uz.pdp.appwarehouseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseproject.entity.Address;
import uz.pdp.appwarehouseproject.entity.Role;

import java.sql.Timestamp;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    // DTO = Data transfer object

    private String firstname;
    private String lastname;
    private String email;
    private Timestamp createdAt;
    private Integer addressId;
    private List<Integer> roleIdList;



}
