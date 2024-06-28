package gr.aueb.cf.employee.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentInsertDTO {
    @NotEmpty(message = "Department name field cannot be empty")
    private String departmentName;
}
