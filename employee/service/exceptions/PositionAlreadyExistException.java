package gr.aueb.cf.employee.service.exceptions;

import gr.aueb.cf.employee.model.Department;
import gr.aueb.cf.employee.model.Position;

import java.io.Serial;

public class PositionAlreadyExistException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public PositionAlreadyExistException(Class<Position> positionClass, String positionName) {
        super("Position " + positionClass.getSimpleName() + " with name: " + positionName + " already exists");
    }
}
