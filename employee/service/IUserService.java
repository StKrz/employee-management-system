package gr.aueb.cf.employee.service;

import gr.aueb.cf.employee.dto.EmployeeInsertDTO;
import gr.aueb.cf.employee.dto.UserInsertDTO;
import gr.aueb.cf.employee.dto.UserUpdateDTO;
import gr.aueb.cf.employee.model.User;
import gr.aueb.cf.employee.service.exceptions.EmployeeAlreadyExistException;
import gr.aueb.cf.employee.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.employee.service.exceptions.UserAlreadyExistsException;
import gr.aueb.cf.employee.service.exceptions.UsernameNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

public interface IUserService {
    User insertUser(UserInsertDTO dto) throws Exception;
//    User updateUser(UserUpdateDTO dto) throws EntityNotFoundException;
    User updateUser(UserUpdateDTO dto) throws EntityNotFoundException;

    User deleteUser(Long id) throws EntityNotFoundException;
    User getUserByUsername(String username) throws UsernameNotFoundException;
    User getUserById(Long id) throws EntityNotFoundException;
    List<User> getAllUsers();
}
