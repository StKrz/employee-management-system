package gr.aueb.cf.employee.controller.admincontrollers;

import gr.aueb.cf.employee.dto.EmployeeUpdateDTO;
import gr.aueb.cf.employee.dto.UserInsertDTO;
import gr.aueb.cf.employee.dto.UserUpdateDTO;
import gr.aueb.cf.employee.model.Employee;
import gr.aueb.cf.employee.model.User;
import gr.aueb.cf.employee.service.*;
import gr.aueb.cf.employee.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.employee.service.exceptions.UserAlreadyExistsException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admin")
//@Slf4j
public class AdminController {

    private final IUserService userService;
    private final IDepartmentService departmentService;
    private final IPositionService positionService;
    private final IEmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminController(IUserService userService, IDepartmentService departmentService, IPositionService positionService, IEmployeeService employeeService) {
        this.userService = userService;
        this.departmentService = departmentService;
        this.positionService = positionService;
        this.employeeService = employeeService;
    }

    @PostMapping("/users/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserInsertDTO userInsertDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(Map.of("message", errorMessages));
        }

        if (!userInsertDTO.getEmail().equals(userInsertDTO.getEmployee().getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("message", "User email and employee email must be the same."));
        }

        try {
            User newUser = userService.insertUser(userInsertDTO);
            newUser.setPassword(passwordEncoder.encode(userInsertDTO.getPassword()));
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "User with this username or email already exists."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred during user registration."));
        }
    }

    @PutMapping("/users/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId,
                                        @Valid @RequestBody UserUpdateDTO userUpdateDTO,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(Map.of("message", errorMessages));
        }

        if (!userUpdateDTO.getEmail().equals(userUpdateDTO.getEmployee().getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("message", "User email and employee email must be the same."));
        }

        userUpdateDTO.setUserId(userId);
        EmployeeUpdateDTO employeeUpdateDTO = userUpdateDTO.getEmployee();
        if (employeeUpdateDTO != null) {
            employeeUpdateDTO.setUserId(userId);
        }

        try {
            User updatedUser = userService.updateUser(userUpdateDTO);
            if (employeeUpdateDTO != null) {
                Employee updatedEmployee = employeeService.updateEmployee(employeeUpdateDTO);
            }
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        try {
            User deletedUser = userService.deleteUser(id);

            System.out.println(deletedUser + " was deleted.");
            return new ResponseEntity<>(deletedUser, HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
