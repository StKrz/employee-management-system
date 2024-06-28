package gr.aueb.cf.employee.mapper;

import gr.aueb.cf.employee.dto.EmployeeUpdateDTO;
import gr.aueb.cf.employee.model.Department;
import gr.aueb.cf.employee.model.Employee;
import gr.aueb.cf.employee.model.Position;
import gr.aueb.cf.employee.model.User;
import gr.aueb.cf.employee.repository.DepartmentRepository;
import gr.aueb.cf.employee.repository.PositionRepository;
import gr.aueb.cf.employee.repository.UserRepository;
import gr.aueb.cf.employee.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    public void updateEmployeeFromDto(Employee employee, EmployeeUpdateDTO dto) throws EntityNotFoundException {
        employee.setFirstname(dto.getFirstname());
        employee.setLastname(dto.getLastname());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setSalary(dto.getSalary());

        employee.setDepartment(departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException(Employee.class, dto.getDepartmentId())));
        employee.setPosition(positionRepository.findById(dto.getPositionId())
                .orElseThrow(() -> new EntityNotFoundException(Employee.class, dto.getPositionId())));
    }
}
