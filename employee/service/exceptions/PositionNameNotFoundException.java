package gr.aueb.cf.employee.service.exceptions;

import java.io.Serial;

public class PositionNameNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 2L;
    public PositionNameNotFoundException(Class<?> entityClass, String positionName) {
        super("Position " + entityClass.getSimpleName() + " with name " + positionName + " was not found");
    }
}
