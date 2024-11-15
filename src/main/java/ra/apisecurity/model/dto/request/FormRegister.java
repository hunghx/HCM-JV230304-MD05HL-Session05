package ra.apisecurity.model.dto.request;

import lombok.Data;

@Data
public class FormRegister {
    private String username;
    private String password;
    private Boolean status;
    private String fullName;
}
