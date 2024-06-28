package gr.aueb.cf.employee.mapper;

import gr.aueb.cf.employee.dto.*;
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

import java.time.LocalDateTime;

import static java.util.Arrays.stream;

@Component
public class RegisterMapper {

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;

    @Autowired
    public RegisterMapper(DepartmentRepository departmentRepository, PositionRepository positionRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.userRepository = userRepository;
    }

    public User mapToUser(UserInsertDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    public Employee mapToEmployeeInsert(EmployeeInsertDTO dto) throws EntityNotFoundException {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException(Department.class, dto.getDepartmentId()));
        Position position = positionRepository.findById(dto.getPositionId())
                .orElseThrow(() -> new EntityNotFoundException(Position.class, dto.getPositionId()));

        Employee employee = new Employee();
        employee.setFirstname(dto.getFirstname());
        employee.setLastname(dto.getLastname());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setSalary(dto.getSalary());
        employee.setHireDate(LocalDateTime.now());
        employee.setDepartment(department);
        employee.setPosition(position);
        return employee;
    }

    public Department mapToDepartment(DepartmentInsertDTO dto) {
        return new Department(null, dto.getDepartmentName(), null);
    }

    public Position mapToPosition(PositionInsertDTO dto) {
        return new Position(null, dto.getPositionName(), null);
    }
}