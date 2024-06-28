package gr.aueb.cf.employee.service.exceptions;

import gr.aueb.cf.employee.model.Department;

import java.io.Serial;

public class DepartmentNotFountException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public DepartmentNotFountException(Class<Department> departmentClass, String departmentName) {
        super("Department " + departmentName + " already exists.");
    }
}
