package gr.aueb.cf.employee.controller.admincontrollers;

import gr.aueb.cf.employee.dto.DepartmentInsertDTO;
import gr.aueb.cf.employee.model.Department;
import gr.aueb.cf.employee.service.IDepartmentService;
import gr.aueb.cf.employee.service.exceptions.DepartmentAlreadyExistException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@Slf4j
public class DepartmentController {

    private final IDepartmentService departmentService;

    @Autowired
    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments/register")
    public ResponseEntity<?> register(@Valid @ModelAttribute DepartmentInsertDTO departmentInsertDTO) {
        try {
            Department department = departmentService.insertDepartment(departmentInsertDTO);
            return new ResponseEntity<>(department, HttpStatus.CREATED);
        } catch (DepartmentAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Department already exists."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred during department registration."));
        }
    }

//    @PutMapping("/update")
//    public ResponseEntity<Department> update(@RequestBody DepartmentUpdateDTO departmentUpdateDTO) {
//        try {
//            Department updatedDepartment = departmentService.updateDepartment(departmentUpdateDTO);
//            return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Department> deleteDepartment(@PathVariable Long id) {
//        try {
//            departmentService.deleteDepartment(id);
//            return ResponseEntity.noContent().build();
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(404).build(); // Not Found
//        } catch (Exception e) {
//            return ResponseEntity.status(500).build(); // Internal Server Error
//        }
//    }
//
//    @GetMapping("/id/{id}")
//    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
//        log.info("Received request to get department by id: " + id);
//        try {
//            Department department = departmentService.getDepartmentById(id);
//            log.info("Department found: " + department);
//            return new ResponseEntity<>(department, HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//            log.error("Department not found with id: " + id, e);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            log.error("An error occurred while fetching the department with id: " + id, e);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/{departmentName}")
//    public ResponseEntity<Department> getDepartmentById(@PathVariable String departmentName) {
//        log.info("Received request to get department by name: " + departmentName);
//        try {
//            Department department = departmentService.getDepartmentByName(departmentName);
//            log.info("Department found: " + department);
//            return new ResponseEntity<>(department, HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//            log.error("Department not found with name: " + departmentName, e);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            log.error("An error occurred while fetching the department with name: " + departmentName, e);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/departments/all")
//    public ResponseEntity<List<Department>> getAllDepartments() {
//        try {
//            List<Department> departments = departmentService.getAllDepartments();
//            return new ResponseEntity<>(departments, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
