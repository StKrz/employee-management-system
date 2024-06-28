package gr.aueb.cf.employee.service;

import gr.aueb.cf.employee.dto.EmployeeInsertDTO;
import gr.aueb.cf.employee.dto.EmployeeUpdateDTO;
import gr.aueb.cf.employee.dto.UserInsertDTO;
import gr.aueb.cf.employee.dto.UserUpdateDTO;
import gr.aueb.cf.employee.mapper.EmployeeMapper;
import gr.aueb.cf.employee.mapper.RegisterMapper;
import gr.aueb.cf.employee.mapper.UserMapper;
import gr.aueb.cf.employee.model.Employee;
import gr.aueb.cf.employee.model.User;
import gr.aueb.cf.employee.repository.DepartmentRepository;
import gr.aueb.cf.employee.repository.EmployeeRepository;
import gr.aueb.cf.employee.repository.PositionRepository;
import gr.aueb.cf.employee.repository.UserRepository;
import gr.aueb.cf.employee.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.employee.service.exceptions.UserAlreadyExistsException;
import gr.aueb.cf.employee.service.exceptions.UsernameNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final RegisterMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private  final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final UserMapper userMapper;
    private final EmployeeMapper employeeMapper;

    public UserServiceImpl(UserRepository userRepository, RegisterMapper mapper, PasswordEncoder passwordEncoder, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, PositionRepository positionRepository, UserMapper userMapper, EmployeeMapper employeeMapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.userMapper = userMapper;
        this.employeeMapper = employeeMapper;
    }

    @Override
    @Transactional
    public User insertUser(UserInsertDTO dto) throws Exception {
        try {
            if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
                throw new UserAlreadyExistsException(User.class, dto.getUsername());
            }
            if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new UserAlreadyExistsException(User.class, dto.getEmail());
            }

            User user = mapper.mapToUser(dto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            EmployeeInsertDTO employeeDto = dto.getEmployee();
            Employee employee = mapper.mapToEmployeeInsert(employeeDto);
            employee.setUser(user);
            user.setEmployee(employee);

            userRepository.save(user);
            employeeRepository.save(employee);
            return user;
        } catch (UserAlreadyExistsException e) {
            log.error("User with id already exists", e);
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during inserting the user", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public User updateUser(UserUpdateDTO dto) throws EntityNotFoundException {
        if (dto.getUserId() == null) {
            throw new EntityNotFoundException(User.class, null);
        }

        User existingUser = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(User.class, dto.getUserId()));

        userMapper.updateUserFromDto(existingUser, dto);
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        EmployeeUpdateDTO employeeDTO = dto.getEmployee();
        if (employeeDTO != null) {
            Employee existingEmployee = existingUser.getEmployee();
            if (existingEmployee == null) {
                existingEmployee = new Employee();
                existingUser.setEmployee(existingEmployee);
            }
            employeeMapper.updateEmployeeFromDto(existingEmployee, employeeDTO);
        }
        Employee existingEmployee = existingUser.getEmployee();

        userRepository.save(existingUser);
        employeeRepository.save(existingEmployee);
        log.info("User with id " + existingUser.getUserId() + " updated");
        return existingUser;
    }

    @Override
    @Transactional
    public User deleteUser(Long id) throws EntityNotFoundException {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(User.class, id));

            userRepository.deleteById(id);
            log.info("User with id: " + id + " deleted");
            return user;
        } catch (EntityNotFoundException e) {
            log.error("User not found",e);
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during deleting the user", e);
            throw e;
        }
    }

    @Override
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(User.class, username));
            log.info("User with username {" + username + "} found");
            return user;
        } catch (UsernameNotFoundException e) {
            log.error("User " + username + " not found", e);
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during user retrieval by username", e);
            throw e;
        }
    }

    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(User.class, id));
            log.info("User with id " + user.getUserId() + " found");
            return user;
        } catch (EntityNotFoundException e) {
            log.error("User not found with id: " + id, e);
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during user retrieval with id: " + id, e);
            throw e;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            log.info("Retrieved all users");
            return users;
        } catch (Exception e) {
            log.error("An error occurred during retrieving all users");
            throw e;
        }
    }
}
