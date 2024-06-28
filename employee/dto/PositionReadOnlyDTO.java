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
public class PositionReadOnlyDTO extends BasePositionDTO {
    private String positionName;
    private List<Employee> employees;
}
