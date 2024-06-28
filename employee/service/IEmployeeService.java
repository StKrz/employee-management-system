package gr.aueb.cf.employee.service;

import gr.aueb.cf.employee.dto.EmployeeInsertDTO;
import gr.aueb.cf.employee.dto.EmployeeUpdateDTO;
import gr.aueb.cf.employee.model.Department;
import gr.aueb.cf.employee.model.Employee;
import gr.aueb.cf.employee.model.User;
import gr.aueb.cf.employee.service.exceptions.EmployeeAlreadyExistException;
import gr.aueb.cf.employee.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.employee.service.exceptions.LastnameNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

public interface IEmployeeService {
    Employee insertEmployee(EmployeeInsertDTO dto) throws EmployeeAlreadyExistException, Exception;

    Employee updateEmployee(EmployeeUpdateDTO dto) throws EntityNotFoundException;

    Employee deleteEmployee(Long id) throws EntityNotFoundException;

    Employee getEmployeeByLastname(String lastname) throws LastnameNotFoundException;

    Employee getEmployeeById(Long id) throws EntityNotFoundException;

    List<Employee> getAllEmployees();

    //    Employee getEmployeeByUser(User user) throws EntityNotFoundException;
    Employee getEmployeeByEmail(String email) throws Exception;

    Employee getEmployeeByUser(User user) throws EntityNotFoundException;

    public List<Employee> findByDepartment(Department department) throws EntityNotFoundException;
}
