package ra.apisecurity.model.dto.response;

import lombok.Data;

@Data
public class JWTResponse {
    private final String type = "Bearer Token";
    private String accessToken;
    private String refreshToken;
    private UserInfo user;
}
