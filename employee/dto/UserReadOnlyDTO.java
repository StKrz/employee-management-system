package gr.aueb.cf.employee.dto;

import gr.aueb.cf.employee.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserReadOnlyDTO extends BaseUserDTO {
    private String username;
    private String email;
    private String password;
    private Role role;
}
