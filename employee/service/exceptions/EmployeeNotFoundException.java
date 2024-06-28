package gr.aueb.cf.employee.service.exceptions;

import gr.aueb.cf.employee.model.Employee;

import java.io.Serial;

public class EmployeeNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    public EmployeeNotFoundException(Class<?> employeeClass, Long id) {
        super("Entity " + employeeClass.getSimpleName() + " with id " + id + " was not found");
    }
}
