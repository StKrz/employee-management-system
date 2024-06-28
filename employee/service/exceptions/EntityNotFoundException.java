package gr.aueb.cf.employee.service.exceptions;


import java.io.Serial;

public class EntityNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    public EntityNotFoundException(Class<?> entityClass, Long id) {
        super("Entity " + entityClass.getSimpleName() + " with id " + id + " was not found");
    }
}
