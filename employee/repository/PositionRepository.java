package gr.aueb.cf.employee.repository;

import gr.aueb.cf.employee.model.Employee;
import gr.aueb.cf.employee.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {
    Optional<Position> findByPositionName(String positionName);
}
