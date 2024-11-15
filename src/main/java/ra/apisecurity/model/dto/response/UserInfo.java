package ra.apisecurity.model.dto.response;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import ra.apisecurity.model.entity.Role;
@Data
public class UserInfo {
    private Long id;
    private String username;
    private Boolean status;
    private String fullName;
    private String role;
}
