package gr.aueb.cf.employee.service;

import gr.aueb.cf.employee.dto.DepartmentInsertDTO;
import gr.aueb.cf.employee.dto.DepartmentUpdateDTO;
import gr.aueb.cf.employee.mapper.RegisterMapper;
import gr.aueb.cf.employee.model.Department;
import gr.aueb.cf.employee.repository.DepartmentRepository;
import gr.aueb.cf.employee.service.exceptions.DepartmentAlreadyExistException;
import gr.aueb.cf.employee.service.exceptions.DepartmentNameNotFoundException;
import gr.aueb.cf.employee.service.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class DepartmentServiceImpl implements IDepartmentService {
    private final DepartmentRepository departmentRepository;
    private final RegisterMapper mapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, RegisterMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Department insertDepartment(DepartmentInsertDTO dto) throws DepartmentAlreadyExistException {
        try {
            if (dto == null) throw new IllegalArgumentException("Cannot be null");

            Optional<Department> returnedDepartment = departmentRepository.findByDepartmentName(dto.getDepartmentName());

            if (returnedDepartment.isPresent()) throw new DepartmentAlreadyExistException(Department.class, dto.getDepartmentName());

            Department department = mapper.mapToDepartment(dto);
            departmentRepository.save(department);
            log.info("Department with id " + department.getDepartmentId() + " successfully inserted!");
            return department;

//            if (dto == null) throw new IllegalArgumentException("Cannot be null");
//
//            Department department = mapper.mapToDepartment(dto);
//            Optional<Department> returnedDepartment = departmentRepository.findByDepartmentName(department.getDepartmentName());
//
//            if (returnedDepartment.isPresent()) throw new DepartmentAlreadyExistException(Department.class, department.getDepartmentName());
//
//            departmentRepository.save(department);
//            log.info("Position with id " + department.getDepartmentId() + " successfully inserted!");
//            return department;
        } catch (DepartmentAlreadyExistException e) {
            log.error("Department Already Exist");
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during inserting the employee", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public Department updateDepartment(DepartmentUpdateDTO dto) throws EntityNotFoundException {
        try {
            log.info("Updating department with id: " + dto.getDepartmentId());

            if (dto.getDepartmentId() == null) throw new EntityNotFoundException(Department.class, null);

            Department department = departmentRepository.findDepartmentByDepartmentId(dto.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException(Department.class, dto.getDepartmentId()));

            log.info("Found department: " + department);

            department.setDepartmentName(dto.getDepartmentName());
            Department updatedDepartment = departmentRepository.save(department);

            log.info("Department with id " + department.getDepartmentName() + " successfully updated!");
            return updatedDepartment;
        } catch (EntityNotFoundException e) {
            log.error("Department not found");
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during department update", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public Department deleteDepartment(Long id) throws EntityNotFoundException {
        try {
            Department department = departmentRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(Department.class, id));

            departmentRepository.delete(department);
            log.info("Department with id " + id + " successfully deleted!");
            return department;
        } catch (EntityNotFoundException e) {
            log.error("Department not found");
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during deleting the department", e);
            throw e;
        }
    }

    @Override
    public Department getDepartmentByName(String departmentName) throws DepartmentNameNotFoundException {
        try {
            return departmentRepository.findByDepartmentName(departmentName)
                    .orElseThrow(() -> new DepartmentNameNotFoundException(Department.class, departmentName));
        } catch (DepartmentNameNotFoundException e) {
            log.error("Department not found");
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during deleting the position");
            throw e;
        }
    }

    @Override
    public Department getDepartmentById(Long id) throws EntityNotFoundException {
        Department department;

        try {
            department = departmentRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(Department.class, id));
        } catch (EntityNotFoundException e) {
            log.error("Department not found");
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during deleting the department");
            throw e;
        }
        return department;
    }

    @Override
    public List<Department> getAllDepartments() {
        try {
            List<Department> department = departmentRepository.findAll();
            log.info("Retrieved all departments!");
            return department;
        } catch (Exception e) {
            log.error("An error occurred during retrieving all departments", e);
            throw e;
        }
    }
}
