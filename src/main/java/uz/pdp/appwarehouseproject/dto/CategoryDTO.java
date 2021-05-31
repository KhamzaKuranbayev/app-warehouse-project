package uz.pdp.appwarehouseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseproject.entity.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private String name;
    private String description;
    private Integer parentId;

}
