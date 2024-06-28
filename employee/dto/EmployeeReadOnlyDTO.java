package gr.aueb.cf.employee.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeReadOnlyDTO extends BaseEmployeeDTO {
    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime hireDate;
    private String phoneNumber;
    private Double salary;
    private Long departmentId;
    private Long positionId;
}
