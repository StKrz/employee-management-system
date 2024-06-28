package gr.aueb.cf.employee.controller;

import gr.aueb.cf.employee.dto.*;
import gr.aueb.cf.employee.mapper.RegisterMapper;
import gr.aueb.cf.employee.model.*;
import gr.aueb.cf.employee.service.*;
import gr.aueb.cf.employee.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.employee.service.exceptions.UsernameNotFoundException;
import gr.aueb.cf.employee.webtoken.JWTService;
import gr.aueb.cf.employee.webtoken.LoginForm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class GeneralController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private PositionServiceImpl positionService;

    @Autowired
    private DepartmentServiceImpl departmentService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final RegisterMapper mapper;

    public GeneralController(PositionServiceImpl positionService, DepartmentServiceImpl departmentService, IUserService userService, IEmployeeService employeeService, RegisterMapper mapper) {
        this.positionService = positionService;
        this.departmentService = departmentService;
        this.userService = userService;
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public String login(Model model, Principal principal, HttpServletRequest request) throws UsernameNotFoundException {
        if (principal == null) return "login";

        var user = userService.getUserByUsername(principal.getName());
        String role = user.getRole().name();

        if (role.equals("ADMIN")) {
            return "redirect:/admin/home";
        }

        if (role.equals("EMPLOYEE")) {
            return "redirect:/employees/home";
        }

        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);
        return "dashboard";
    }

    @GetMapping(path = { "/" })
    String root(Model model, Principal principal, HttpServletRequest request) throws Exception {
        return "redirect:/login";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "admin_home";
    }

    @GetMapping("/employees/home")
    public String showEmployeeDetails(Model model, Principal principal) throws UsernameNotFoundException {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        if (user != null) {
            model.addAttribute("user", user);
            return "employee_home";
        }
        model.addAttribute("error", "User not found.");
        return "404";
    }

    @GetMapping("/employees/coworkers")
    public String viewColleagues(Model model, Principal principal) throws UsernameNotFoundException, EntityNotFoundException {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        if (user != null && user.getEmployee() != null) {
            Department department = user.getEmployee().getDepartment();
            List<Employee> colleagues = employeeService.findByDepartment(department);
            model.addAttribute("colleagues", colleagues);
            return "employee_coworkers_list";
        }

        model.addAttribute("404", "User or department not found.");
        return "404";
    }

    @GetMapping("/admin/employees/coworkers")
    public String viewAdminColleagues(Model model, Principal principal) throws UsernameNotFoundException, EntityNotFoundException {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        if (user != null && user.getEmployee() != null) {
            Department department = user.getEmployee().getDepartment();
            List<Employee> colleagues = employeeService.findByDepartment(department);
            model.addAttribute("colleagues", colleagues);
            return "admin_employee_coworkers_list";
        }

        model.addAttribute("404", "User or department not found.");
        return "404";
    }

    @GetMapping("/admin/users/register")
    public String createNewUser(Model model) {
        UserInsertDTO userInsertDTO = new UserInsertDTO();
        List<Department> departments = departmentService.getAllDepartments();
        List<Position> positions = positionService.getAllPositions();

        model.addAttribute("user", userInsertDTO);
        model.addAttribute("departments", departments);
        model.addAttribute("positions", positions);
        return "create_employee";
    }

    @GetMapping("/admin/departments/register")
    public String createNewDepartment(Model model) {
        DepartmentInsertDTO departmentInsertDTO = new DepartmentInsertDTO();
        model.addAttribute("department", departmentInsertDTO);
        return "create_department";
    }

    @GetMapping("/admin/positions/register")
    public String createNewPosition(Model model) {
        PositionInsertDTO positionInsertDTO = new PositionInsertDTO();
        model.addAttribute("position", positionInsertDTO);
        return "create_position";
    }

    @GetMapping("/admin/users/update/{userId}")
    public String showUpdateUserForm(@PathVariable("userId") Long userId, Model model) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new EntityNotFoundException(User.class, userId);
            }

            UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
//            userUpdateDTO.setUserId(user.getUserId());
            userUpdateDTO.setUsername(user.getUsername());
            userUpdateDTO.setEmail(user.getEmail());
            userUpdateDTO.setPassword(passwordEncoder.encode(user.getPassword()));
            userUpdateDTO.setRole(user.getRole());

            Employee employee = user.getEmployee();
            EmployeeUpdateDTO employeeUpdateDTO = new EmployeeUpdateDTO();
//            employeeUpdateDTO.setEmployeeId(employee.getId());
            employeeUpdateDTO.setFirstname(employee.getFirstname());
            employeeUpdateDTO.setLastname(employee.getLastname());
            employeeUpdateDTO.setEmail(employee.getEmail());
            employeeUpdateDTO.setPhoneNumber(employee.getPhoneNumber());
            employeeUpdateDTO.setSalary(employee.getSalary());
            employeeUpdateDTO.setDepartmentId(employee.getDepartment().getDepartmentId());
            employeeUpdateDTO.setPositionId(employee.getPosition().getPositionId());
            employeeUpdateDTO.setUserId(user.getUserId());

            userUpdateDTO.setEmployee(employeeUpdateDTO);

            List<Department> departments = departmentService.getAllDepartments();
            List<Position> positions = positionService.getAllPositions();
            List<Role> roles = Arrays.asList(Role.values());

            model.addAttribute("userUpdateDTO", userUpdateDTO);
            model.addAttribute("departments", departments);
            model.addAttribute("positions", positions);
            model.addAttribute("roles", roles);

            model.addAttribute("userUpdateDTO", userUpdateDTO);
            model.addAttribute("employeeUpdateDTO", employeeUpdateDTO);

            return "update_employee";
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "User not found");
            return "ENTITY NOT FOUND";
        }
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.username(), loginForm.password()
        ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userDetailService.loadUserByUsername(loginForm.username()));
        } else {
            throw new org.springframework.security.core.userdetails.UsernameNotFoundException("Invalid credentials");
        }
    }
}


