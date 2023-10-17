package project.accede.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {

    private String username;
    private String password;
    private boolean rememberMe;

    public boolean getRememberMe () {
        return this.rememberMe;
    }
}
