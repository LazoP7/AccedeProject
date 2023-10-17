package project.accede.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtDTO {

    private String accessToken;
    private String refreshToken;
}
