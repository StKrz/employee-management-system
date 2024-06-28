package gr.aueb.cf.employee.service.exceptions;

import gr.aueb.cf.employee.model.User;

import java.io.Serial;

public class UserAlreadyExistsException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(Class<User> userClass, String username) {
        super("User with username " + username + " already exists");
    }
}
