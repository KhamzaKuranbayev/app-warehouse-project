package uz.pdp.appwarehouseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String message;     // xabar
    private boolean success;    // xabar holati
    private Object object;      // content

    public Response(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
