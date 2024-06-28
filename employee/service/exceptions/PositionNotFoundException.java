package gr.aueb.cf.employee.service.exceptions;

import gr.aueb.cf.employee.model.Position;

import java.io.Serial;

public class PositionNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    public PositionNotFoundException(Class<Position> positionClass, Long id) {
        super("Entity " + positionClass.getSimpleName() + " with id " + id + " was not found");
    }
}
