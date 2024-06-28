package gr.aueb.cf.employee.service.exceptions;

import java.io.Serial;

public class UsernameNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 2L;
    public UsernameNotFoundException(Class<?> entityClass, String username) {
        super("Entity " + entityClass.getSimpleName() + " with username " + username + " was not found");
    }
}
