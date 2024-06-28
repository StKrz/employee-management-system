package gr.aueb.cf.employee.service.exceptions;

import java.io.Serial;

public class LastnameNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 2L;
    public LastnameNotFoundException(Class<?> entityClass, String lastname) {
        super("Employee " + entityClass.getSimpleName() + " with lastname " + lastname + " was not found");
    }
}
