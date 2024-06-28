package gr.aueb.cf.employee.service;

import gr.aueb.cf.employee.dto.DepartmentInsertDTO;
import gr.aueb.cf.employee.dto.DepartmentUpdateDTO;
import gr.aueb.cf.employee.model.Department;
import gr.aueb.cf.employee.service.exceptions.DepartmentNameNotFoundException;
import gr.aueb.cf.employee.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IDepartmentService {
    Department insertDepartment(DepartmentInsertDTO dto) throws Exception;
    Department updateDepartment(DepartmentUpdateDTO dto) throws EntityNotFoundException;
    Department deleteDepartment(Long id) throws EntityNotFoundException;
    Department getDepartmentByName(String departmentName) throws EntityNotFoundException, DepartmentNameNotFoundException;
    Department getDepartmentById(Long id) throws EntityNotFoundException;
    List<Department> getAllDepartments();
}
