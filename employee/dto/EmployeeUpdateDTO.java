package gr.aueb.cf.employee.dto;

import gr.aueb.cf.employee.model.Department;
import gr.aueb.cf.employee.model.Position;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeUpdateDTO extends BaseEmployeeDTO {

//    @NotNull(message = "Employee ID cannot be null")
    private Long employeeId;

//    @NotNull(message = "First name cannot be null")
//    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    private String firstname;

//    @NotNull(message = "Last name cannot be null")
//    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String lastname;

//    @NotNull(message = "Email cannot be null")
//    @Email(message = "Email should be valid")
//    @Size(max = 100, message = "Email must be at most 100 characters")
    private String email;

//    @NotNull(message = "Phone number cannot be null")
//    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number is not valid")
//    @Size(min = 7, max = 25, message = "Phone number must be between 7 and 25 characters")
    private String phoneNumber;

//    @PositiveOrZero(message = "Salary must be positive or zero")
    private Double salary;

//    @NotNull(message = "Department ID cannot be null")
    private Long departmentId;

//    @NotNull(message = "Position ID cannot be null")
    private Long positionId;

    private Long userId;
}
