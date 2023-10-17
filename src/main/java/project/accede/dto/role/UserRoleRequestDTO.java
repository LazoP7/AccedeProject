package project.accede.dto.role;

import lombok.Data;

import java.util.List;

@Data
public class UserRoleRequestDTO {

    private int userId;
    private List<RoleDTO> roles;
}
