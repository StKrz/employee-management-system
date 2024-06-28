package gr.aueb.cf.employee.service.exceptions;

import gr.aueb.cf.employee.model.Department;

import java.io.Serial;

public class DepartmentAlreadyExistException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public DepartmentAlreadyExistException(Class<Department> employeeClass, String departementName) {
        super("Department " + departementName + " already exists");
    }
}
