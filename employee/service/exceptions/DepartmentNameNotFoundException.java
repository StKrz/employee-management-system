package gr.aueb.cf.employee.service.exceptions;

import java.io.Serial;

public class DepartmentNameNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 2L;
    public DepartmentNameNotFoundException(Class<?> entityClass, String departmentName) {
        super("Department " + entityClass.getSimpleName() + " with name " + departmentName + " was not found");
    }
}
