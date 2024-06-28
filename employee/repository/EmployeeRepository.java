package gr.aueb.cf.employee.repository;

import gr.aueb.cf.employee.model.Department;
import gr.aueb.cf.employee.model.Employee;
import gr.aueb.cf.employee.model.Position;
import gr.aueb.cf.employee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByLastname(String lastname);
    Optional<Employee> findByUser(User user);
    Optional<Employee> findByEmail(String email);
    List<Employee> findByDepartment(Department department);

}
