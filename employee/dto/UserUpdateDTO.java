package gr.aueb.cf.employee.dto;

import gr.aueb.cf.employee.model.Employee;
import gr.aueb.cf.employee.model.Role;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateDTO extends BaseUserDTO {

//    @NotNull(message = "Username is mandatory")
//    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

//    @NotNull(message = "Username is mandatory")
//    @Email(message = "Email should be valid")
    private String email;

//    @NotNull
//    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

//    @Enumerated
    private Role role;

    private EmployeeUpdateDTO employee;
}
