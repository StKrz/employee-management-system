package gr.aueb.cf.employee.dto;

import gr.aueb.cf.employee.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentReadOnlyDTO extends BaseDepartmentDTO {
    private String departmentName;
    private List<Employee> employees;
}
