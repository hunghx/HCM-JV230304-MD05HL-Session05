package ra.apisecurity.model.dto.request;

import lombok.Data;

@Data
public class FormLogin {
    private String username;
    private String password;
}
