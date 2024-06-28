////package gr.aueb.cf.employee.service;
////
////import gr.aueb.cf.employee.dto.EmployeeInsertDTO;
////import gr.aueb.cf.employee.dto.EmployeeUpdateDTO;
////import gr.aueb.cf.employee.mapper.Mapper;
////import gr.aueb.cf.employee.model.Department;
////import gr.aueb.cf.employee.model.Employee;
////import gr.aueb.cf.employee.model.Position;
////import gr.aueb.cf.employee.repository.DepartmentRepository;
////import gr.aueb.cf.employee.repository.EmployeeRepository;
////import gr.aueb.cf.employee.repository.PositionRepository;
////import gr.aueb.cf.employee.service.exceptions.EmployeeAlreadyExistException;
////import gr.aueb.cf.employee.service.exceptions.EntityNotFoundException;
////import gr.aueb.cf.employee.service.exceptions.LastnameNotFoundException;
////import jakarta.transaction.Transactional;
////import lombok.extern.slf4j.Slf4j;
////import org.apache.catalina.valves.rewrite.Substitution;
////import org.springframework.stereotype.Service;
////
////import java.time.LocalDateTime;
////import java.util.List;
////import java.util.Optional;
////
////@Service
////@Transactional
////@Slf4j
////public class EmployeeServiceImpl implements IEmployeeService {
////    private final EmployeeRepository employeeRepository;
//////    private final Mapper mapper;
////
////    public EmployeeServiceImpl(EmployeeRepository employeeRepository/*, Mapper mapper*/) {
////        this.employeeRepository = employeeRepository;
//////        this.mapper = mapper;
////    }
////    @Override
////    @Transactional
////    public Employee insertEmployee(EmployeeInsertDTO dto) throws EmployeeAlreadyExistException, Exception {
////        try {
////            if (dto == null) throw new IllegalArgumentException("Cannot be null");
////
////            Employee employee = Mapper.mapToEmployeeInsert(dto);
////            Optional<Employee> returnedEmployee = employeeRepository.findByLastname(employee.getLastname());
////
////            if (returnedEmployee.isPresent()) throw new EmployeeAlreadyExistException(Employee.class, employee.getLastname());
////
////            employeeRepository.save(employee);
////            log.info("Employee with id " + employee.getId() + " successfully inserted!");
////            return employee;
////        } catch (EmployeeAlreadyExistException e) {
////            log.error("Department or Position not found");
////            throw e;
////        } catch (Exception e) {
////            log.error("An error occurred during inserting the employee", e);
////            throw e;
////        }
////    }
////
////    @Override
////    @Transactional
////    public Employee updateEmployee(EmployeeUpdateDTO dto) throws EntityNotFoundException {
////        try {
////            if (dto.getEmployeeId() == null) throw new EntityNotFoundException(Employee.class, null);
////
////            Optional<Employee> existingEmployee = employeeRepository.findById(dto.getEmployeeId());
////
////            if (existingEmployee.isEmpty()) throw new EntityNotFoundException(Employee.class, dto.getEmployeeId());
////
////            Employee employee = Mapper.mapToEmployeeUpdate(dto);
////            employeeRepository.save(employee);
////            log.info("Employee with id " + employee.getId() + " successfully updated!");
////            return employee;
////        } catch (EntityNotFoundException e) {
////            log.error("Employee not found");
////            throw e;
////        } catch (Exception e) {
////            log.error("An error occurred during updating the employee", e);
////            throw e;
////        }
////    }
////
////    @Override
////    @Transactional
////    public Employee deleteEmployee(Long id) throws EntityNotFoundException {
////        try {
////            Employee employee = employeeRepository.findById(id)
////                    .orElseThrow(() -> new EntityNotFoundException(Employee.class, id));
////
////            employeeRepository.delete(employee);
////
////            log.info("Employee with id " + id + " successfully deleted!");
////            return employee;
////        } catch (EntityNotFoundException e) {
////            log.error("Employee not found");
////            throw e;
////        } catch (Exception e) {
////            log.error("An error occurred during deleting the employee", e);
////            throw e;
////        }
////    }
////
////    @Override
////    public Employee getEmployeeByLastname(String lastname) throws LastnameNotFoundException {
////        try {
////            Employee employee = employeeRepository.findByLastname(lastname)
////                    .orElseThrow(() -> new LastnameNotFoundException(Employee.class, lastname));
////            log.info("Employee with lastname {" + lastname + "} found");
////            return employee;
////        } catch (LastnameNotFoundException e) {
////            log.error("Employee {" + lastname + "} not found", e);
////            throw e;
////        } catch (Exception e) {
////            log.error("An error occurred during employee retrieval by lastname", e);
////            throw e;
////        }
////    }
////
////    @Override
////    public Employee getEmployeeById(Long id) throws EntityNotFoundException {
////        try {
////            Employee employee = employeeRepository.findById(id)
////                    .orElseThrow(() -> new EntityNotFoundException(Employee.class, id));
////
////            return employee;
////        } catch (EntityNotFoundException e) {
////        log.error("Employee not found");
////        throw e;
////        } catch (Exception e) {
////            log.error("An error occurred during deleting the employee", e);
////        throw e;
////        }
////    }
////
////    @Override
////    public List<Employee> getAllEmployees() {
////        try {
////            List<Employee> employees = employeeRepository.findAll();
////            log.info("Retrieved all employee!");
////            return employees;
////        } catch (Exception e) {
////            log.error("An error occurred during retrieving all employees", e);
////            throw e;
////        }
////    }
////}
//package gr.aueb.cf.employee.service;
//
//import gr.aueb.cf.employee.dto.EmployeeInsertDTO;
//import gr.aueb.cf.employee.dto.EmployeeReadOnlyDTO;
//import gr.aueb.cf.employee.dto.EmployeeUpdateDTO;
//import gr.aueb.cf.employee.mapper.Mapper;
//import gr.aueb.cf.employee.model.Employee;
//import gr.aueb.cf.employee.model.User;
//import gr.aueb.cf.employee.repository.EmployeeRepository;
//import gr.aueb.cf.employee.repository.UserRepository;
//import gr.aueb.cf.employee.service.exceptions.EmployeeAlreadyExistException;
//import gr.aueb.cf.employee.service.exceptions.EntityNotFoundException;
//import gr.aueb.cf.employee.service.exceptions.LastnameNotFoundException;
//import jakarta.transaction.Transactional;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//@Slf4j
//public class EmployeeServiceImpl implements IEmployeeService {
//    private final EmployeeRepository employeeRepository;
//    private final UserRepository userRepository;
//    private final Mapper mapper;
//
//    @Autowired
//    public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserRepository userRepository, Mapper mapper) {
//        this.employeeRepository = employeeRepository;
//        this.userRepository = userRepository;
//        this.mapper = mapper;
//    }
//
//    @Override
//    @Transactional
//    public Employee insertEmployee(EmployeeInsertDTO dto) throws EmployeeAlreadyExistException, Exception {
//        try {
//            if (dto == null) throw new IllegalArgumentException("Cannot be null");
//
//            Optional<Employee> returnedEmployee = employeeRepository.findByLastname(dto.getLastname());
//
//            if (returnedEmployee.isPresent()) throw new EmployeeAlreadyExistException(Employee.class, dto.getLastname());
//
//            User user = userRepository.findById(dto.getUserId())
//                    .orElseThrow(() -> new EntityNotFoundException(User.class, dto.getUserId()));
//
//            Employee employee = mapper.mapToEmployeeInsert(dto);
//            employee.setUser(user);
//
//            employeeRepository.save(employee);
//            log.info("Employee with id " + employee.getId() + " successfully inserted!");
//            return employee;
//        } catch (EmployeeAlreadyExistException e) {
//            log.error("Department or Position not found");
//            throw e;
//        } catch (Exception e) {
//            log.error("An error occurred during inserting the employee", e);
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional
//    public Employee updateEmployee(EmployeeUpdateDTO dto) throws EntityNotFoundException {
//        try {
////            if (dto.getEmployeeId() == null) throw new EntityNotFoundException(Employee.class, null);
////
////            Optional<Employee> existingEmployeeOptional = employeeRepository.findById(dto.getEmployeeId());
////
////            if (existingEmployeeOptional.isEmpty()) throw new EntityNotFoundException(Employee.class, dto.getEmployeeId());
////
////            Employee existingEmployee = existingEmployeeOptional.get();
////            Employee updatedEmployee = mapper.mapToEmployeeUpdate(dto);
////
////            // Keep the original hire date
////            updatedEmployee.setHireDate(existingEmployee.getHireDate());
////
////            employeeRepository.save(updatedEmployee);
////            log.info("Employee with id " + updatedEmployee.getId() + " successfully updated!");
////            return updatedEmployee;
//            if (dto.getEmployeeId() == null) throw new EntityNotFoundException(Employee.class, null);
//
//            Optional<Employee> existingEmployeeOptional = employeeRepository.findById(dto.getEmployeeId());
//
//            if (existingEmployeeOptional.isEmpty()) throw new EntityNotFoundException(Employee.class, dto.getEmployeeId());
//
//            User user = userRepository.findById(dto.getUserId())
//                    .orElseThrow(() -> new EntityNotFoundException(User.class, dto.getUserId()));
//
//            Employee existingEmployee = existingEmployeeOptional.get();
//            Employee updatedEmployee = mapper.mapToEmployeeUpdate(dto);
//
//            // Keep the original hire date
//            updatedEmployee.setHireDate(existingEmployee.getHireDate());
//            updatedEmployee.setUser(user);
//
//            employeeRepository.save(updatedEmployee);
//            log.info("Employee with id " + updatedEmployee.getId() + " successfully updated!");
//            return updatedEmployee;
//        } catch (EntityNotFoundException e) {
//            log.error("Employee not found with id " + dto.getEmployeeId());
//            throw e;
//        } catch (Exception e) {
//            log.error("An error occurred during updating the employee", e);
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional
//    public Employee deleteEmployee(Long id) throws EntityNotFoundException {
//        try {
//            Employee employee = employeeRepository.findById(id)
//                    .orElseThrow(() -> new EntityNotFoundException(Employee.class, id));
//
//            employeeRepository.delete(employee);
//
//            log.info("Employee with id " + id + " successfully deleted!");
//            return employee;
//        } catch (EntityNotFoundException e) {
//            log.error("Employee not found");
//            throw e;
//        } catch (Exception e) {
//            log.error("An error occurred during deleting the employee", e);
//            throw e;
//        }
//    }
//
//    @Override
//    public Employee getEmployeeByLastname(String lastname) throws LastnameNotFoundException {
//        try {
//            Employee employee = employeeRepository.findByLastname(lastname)
//                    .orElseThrow(() -> new LastnameNotFoundException(Employee.class, lastname));
//            log.info("Employee with lastname {" + lastname + "} found");
//
//            return employee;
//        } catch (LastnameNotFoundException e) {
//            log.error("Employee {" + lastname + "} not found", e);
//            throw e;
//        } catch (Exception e) {
//            log.error("An error occurred during employee retrieval by lastname", e);
//            throw e;
//        }
//    }
//
//    @Override
//    public Employee getEmployeeById(Long id) throws EntityNotFoundException {
//        try {
//            Employee employee = employeeRepository.findById(id)
//                    .orElseThrow(() -> new EntityNotFoundException(Employee.class, id));
//
//            return employee;
//        } catch (EntityNotFoundException e) {
//            log.error("Employee not found");
//            throw e;
//        } catch (Exception e) {
//            log.error("An error occurred during retrieving the employee", e);
//            throw e;
//        }
//    }
//
//    @Override
//    public List<Employee> getAllEmployees() {
//        try {
//            List<Employee> employees = employeeRepository.findAll();
//            log.info("Retrieved all employees!");
//            return employees;
//        } catch (Exception e) {
//            log.error("An error occurred during retrieving all employees", e);
//            throw e;
//        }
//    }
//
////    @Override
////    public Employee getEmployeeByUser(User user) throws EntityNotFoundException{
////        return employeeRepository.findByUser(user)
////                .orElseThrow(() -> new EntityNotFoundException(User.class, user.getUserId()));
////    }
//}
package gr.aueb.cf.employee.service;

import gr.aueb.cf.employee.dto.EmployeeInsertDTO;
import gr.aueb.cf.employee.dto.EmployeeUpdateDTO;
import gr.aueb.cf.employee.mapper.EmployeeMapper;
import gr.aueb.cf.employee.mapper.RegisterMapper;
import gr.aueb.cf.employee.model.Department;
import gr.aueb.cf.employee.model.Employee;
import gr.aueb.cf.employee.model.User;
import gr.aueb.cf.employee.repository.EmployeeRepository;
import gr.aueb.cf.employee.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.employee.service.exceptions.EmployeeAlreadyExistException;
import gr.aueb.cf.employee.service.exceptions.LastnameNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final RegisterMapper mapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, RegisterMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Employee insertEmployee(EmployeeInsertDTO dto) throws EmployeeAlreadyExistException, Exception {
        Employee employee;

        try {
            if (dto == null) throw new IllegalArgumentException("Cannot be null");

            Optional<Employee> returnedEmployee = employeeRepository.findByEmail(dto.getEmail());

            if (returnedEmployee.isPresent()) throw new EmployeeAlreadyExistException(Employee.class, dto.getEmail());

            employee = mapper.mapToEmployeeInsert(dto);
            employeeRepository.save(employee);
            log.info("Employee with id " + employee.getId() + " successfully inserted!");
            return employee;
        } catch (EmployeeAlreadyExistException e) {
            log.error("Employee already exists", e);
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during inserting the employee", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public Employee updateEmployee(EmployeeUpdateDTO dto) throws EntityNotFoundException {
        if (dto.getEmployeeId() == null) throw new EntityNotFoundException(Employee.class, null);

        Employee existingEmployee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException(Employee.class, dto.getEmployeeId()));

        employeeMapper.updateEmployeeFromDto(existingEmployee, dto);

        employeeRepository.save(existingEmployee);
        log.info("Employee with id " + existingEmployee.getId() + " successfully updated!");
        return existingEmployee;
    }

//    @Override
//    @Transactional
//    public Employee updateEmployee(EmployeeUpdateDTO dto) throws EntityNotFoundException {
//        try {
//            if (dto.getEmployeeId() == null) throw new EntityNotFoundException(Employee.class, null);
//
//            Optional<Employee> existingEmployeeOptional = employeeRepository.findById(dto.getEmployeeId());
//
//            if (existingEmployeeOptional.isEmpty()) throw new EntityNotFoundException(Employee.class, dto.getEmployeeId());
//
//            Employee existingEmployee = existingEmployeeOptional.get();
//
//            employeeMapper.updateEmployeeFromDto(existingEmployee, dto);
//
//            employeeRepository.save(existingEmployee);
//            log.info("Employee with id " + existingEmployee.getId() + " successfully updated!");
//            return existingEmployee;
//        } catch (EntityNotFoundException e) {
//            log.error("Employee not found with id " + dto.getEmployeeId());
//            throw e;
//        } catch (Exception e) {
//            log.error("An error occurred during updating the employee", e);
//            throw e;
//        }
//    }

    @Override
    @Transactional
    public Employee deleteEmployee(Long id) throws EntityNotFoundException {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(Employee.class, id));

            employeeRepository.delete(employee);

            log.info("Employee with id " + id + " successfully deleted!");
            return employee;
        } catch (EntityNotFoundException e) {
            log.error("Employee not found", e);
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during deleting the employee", e);
            throw e;
        }
    }

    @Override
    public Employee getEmployeeByLastname(String lastname) throws LastnameNotFoundException {
        try {
            Employee employee = employeeRepository.findByLastname(lastname)
                    .orElseThrow(() -> new LastnameNotFoundException(Employee.class, lastname));
            log.info("Employee with lastname {" + lastname + "} found");
            return employee;
        } catch (LastnameNotFoundException e) {
            log.error("Employee {" + lastname + "} not found", e);
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during employee retrieval by lastname", e);
            throw e;
        }
    }

    @Override
    public Employee getEmployeeById(Long id) throws EntityNotFoundException {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(Employee.class, id));

            return employee;
        } catch (EntityNotFoundException e) {
            log.error("Employee not found");
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during retrieving the employee", e);
            throw e;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            log.info("Retrieved all employees!");
            return employees;
        } catch (Exception e) {
            log.error("An error occurred during retrieving all employees", e);
            throw e;
        }
    }

    @Override
    public Employee getEmployeeByEmail(String email) throws Exception {
        try {
            Employee employee = employeeRepository.findByEmail(email)
                    .orElseThrow(() -> new Exception("Employee not found"));
            log.info("Employee with email {" + email + "} found");
            return employee;
        } catch (Exception e) {
            log.error("Employee with email {} not found", email);
            throw e;
        }
//        return employeeRepository.findByEmail(email)
//                .orElseThrow(() -> new Exception("Employee not found"));
    }

    @Override
    public Employee getEmployeeByUser(User user) throws EntityNotFoundException {
        try {
            Employee employee = employeeRepository.findByUser(user)
                    .orElseThrow(() -> new EntityNotFoundException(User.class, user.getUserId()));
            log.info("Employee with lastname {" + user.getEmployee().getLastname() + "} found");
            return employee;
        } catch (EntityNotFoundException e) {
            log.error("Employee with lastname {} not found", user.getEmployee().getLastname());
            throw e;
        }
    }

    @Override
    public List<Employee> findByDepartment(Department department) {
        return employeeRepository.findByDepartment(department);
    }
}
