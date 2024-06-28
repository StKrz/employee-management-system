package gr.aueb.cf.employee.controller.admincontrollers;

import gr.aueb.cf.employee.dto.EmployeeInsertDTO;
import gr.aueb.cf.employee.dto.EmployeeUpdateDTO;
import gr.aueb.cf.employee.dto.UserInsertDTO;
import gr.aueb.cf.employee.model.Employee;
import gr.aueb.cf.employee.model.User;
import gr.aueb.cf.employee.service.EmployeeServiceImpl;
import gr.aueb.cf.employee.service.IEmployeeService;
import gr.aueb.cf.employee.service.UserServiceImpl;
import gr.aueb.cf.employee.service.exceptions.EmployeeAlreadyExistException;
import gr.aueb.cf.employee.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.employee.service.exceptions.LastnameNotFoundException;
import gr.aueb.cf.employee.service.exceptions.UsernameNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/employees")
public class EmployeeController {

    private final IEmployeeService employeeService;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<Employee> register(@RequestBody @Valid EmployeeInsertDTO employeeInsertDTO) {
//        try {
//            Employee newEmployee = employeeService.insertEmployee(employeeInsertDTO);
//            return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
//        } catch (EmployeeAlreadyExistException e) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/register")
//    public String showCreateUserEmployeeForm(Model model) {
//        model.addAttribute("user", new UserInsertDTO());
//        model.addAttribute("employee", new EmployeeInsertDTO());
//        return "create_employee";
//    }



//    @PostMapping("/admin/users/register")
//    public String handCreateUserEmployee(@ModelAttribute("user") UserInsertDTO userInsertDTO,
//                                         @ModelAttribute("employee") EmployeeInsertDTO employeeInsertDTO) throws Exception {
//        userService.insertUser(userInsertDTO);
//        employeeInsertDTO.setUserId(userInsertDTO.getEmployee().getUserId());
//        employeeServiceImpl.insertEmployee(employeeInsertDTO);
//        return "redirect:/admin/home";
//    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Employee> deleteUser(@PathVariable Long id) {
//        try {
//            Employee deletedEmployee = employeeService.deleteEmployee(id);
//            return new ResponseEntity<>(deletedEmployee, HttpStatus.NO_CONTENT);
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @DeleteMapping("/delete/{id}")
//    public String deleteEmployee(@PathVariable Long id) throws EntityNotFoundException {
//        employeeService.deleteEmployee(id);
//        return "redirect:/admin/home";
//    }

//    @GetMapping("/lastname/{lastname}")
//    public ResponseEntity<Employee> getEmployeeByLastname(@PathVariable String lastname) {
//        try {
//            Employee employee = employeeService.getEmployeeByLastname(lastname);
//            return new ResponseEntity<>(employee, HttpStatus.OK);
//        } catch (LastnameNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/id/{id}")
//    public ResponseEntity<Employee> getUserById(@PathVariable Long id) {
//        try {
//            Employee employee = employeeService.getEmployeeById(id);
//            return new ResponseEntity<>(employee, HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Employee>> getAllUsers() {
//        try {
//            List<Employee> employees = employeeService.getAllEmployees();
//            return new ResponseEntity<>(employees, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/employee/{employeeId}")
//    public ResponseEntity<Employee> getEmployeeByUser(@PathVariable User user) {
//        try {
//            Employee employee = employeeService.getEmployeeByUser(user);
//            return new ResponseEntity<>(employee, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
