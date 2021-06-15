package uz.pdp.appwarehouseproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseproject.enums.ActionType;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTO {


      private  Integer userId;
      private Integer productId;
    private Integer amount;
    private Double price;
    private Timestamp date;
    private ActionType actionType;




}
