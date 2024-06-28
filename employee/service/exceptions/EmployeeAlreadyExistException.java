package gr.aueb.cf.employee.service.exceptions;

import gr.aueb.cf.employee.model.Employee;
import gr.aueb.cf.employee.model.User;

import java.io.Serial;

public class EmployeeAlreadyExistException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public EmployeeAlreadyExistException(Class<Employee> employeeClass, String lastname) {
        super("Employee with lastname " + lastname + " already exists");
    }
}
