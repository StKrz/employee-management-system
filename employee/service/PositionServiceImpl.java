package gr.aueb.cf.employee.service;

import gr.aueb.cf.employee.dto.PositionInsertDTO;
import gr.aueb.cf.employee.mapper.RegisterMapper;
import gr.aueb.cf.employee.model.Position;
import gr.aueb.cf.employee.repository.PositionRepository;
import gr.aueb.cf.employee.service.exceptions.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PositionServiceImpl implements IPositionService {
    private final PositionRepository positionRepository;
    private final RegisterMapper mapper;

    public PositionServiceImpl(PositionRepository positionRepository, RegisterMapper mapper) {
        this.positionRepository = positionRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Position insertPosition(PositionInsertDTO dto) throws PositionAlreadyExistException {
        try {
            if (dto == null) throw new IllegalArgumentException("Cannot be null");

            Optional<Position> returnedPosition = positionRepository.findByPositionName(dto.getPositionName());

            if (returnedPosition.isPresent()) throw new PositionAlreadyExistException(Position.class, dto.getPositionName());

            Position position = mapper.mapToPosition(dto);
            positionRepository.save(position);
            log.info("Position with id " + position.getPositionId() + " successfully inserted!");
            return position;
        } catch (PositionAlreadyExistException e) {
            log.error("Position Already Exist");
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during inserting the employee", e);
            throw e;
        }
    }

    @Override
    public List<Position> getAllPositions() {
        try {
            List<Position> positions = positionRepository.findAll();
            log.info("Retrieved all position!");
            return positions;
        } catch (Exception e) {
            log.error("An error occurred during retrieving all employees", e);
            throw e;
        }
    }

//    @Override
//    @Transactional
//    public Position updatePosition(PositionUpdateDTO dto) throws EntityNotFoundException {
//        try {
//            log.info("Updating position with id: " + dto.getPositionId());
//
//            if (dto.getPositionId() == null) throw new EntityNotFoundException(Position.class, null);
//
//            Position position = positionRepository.findPositionByPositionId(dto.getPositionId())
//                    .orElseThrow(() -> new EntityNotFoundException(Position.class, dto.getPositionId()));
//
//            log.info("Found position: " + position);
//
//            position.setPositionName(dto.getPositionName());
//            Position updatedPosition = positionRepository.save(position);
//
//            log.info("Position with id " + position.getPositionId() + " successfully updated!");
//            return updatedPosition;
//        } catch (EntityNotFoundException e) {
//            log.error("Position not found", e);
//            throw e;
//        } catch (Exception e) {
//            log.error("An error occurred during position update", e);
//            throw e;
//        }
//    }

//    @Override
//    @Transactional
//    public Position deletePosition(Long id) throws EntityNotFoundException {
//        try {
//            Position position = positionRepository.findById(id)
//                    .orElseThrow(() -> new EntityNotFoundException(Position.class, id));
//
//            positionRepository.delete(position);
//            log.info("Position with id " + id + " successfully deleted!");
//            return position;
//        } catch (EntityNotFoundException e) {
//            log.error("Position not found");
//            throw e;
//        } catch (Exception e) {
//            log.error("An error occurred during deleting the employee", e);
//            throw e;
//        }
//    }

//    @Override
//    public Position getPositionByName(String positionName) throws PositionNameNotFoundException {
//        try {
//            return positionRepository.findByPositionName(positionName)
//                    .orElseThrow(() -> new PositionNameNotFoundException(Position.class, positionName));
//        } catch (PositionNameNotFoundException e) {
//            log.error("Position not found");
//            throw e;
//        } catch (Exception e) {
//            log.error("An error occurred during deleting the position");
//            throw e;
//        }
//    }
//
//    @Override
//    public Position getPositionById(Long id) throws EntityNotFoundException {
//        Position position;
//
//        try {
//            position = positionRepository.findById(id)
//                    .orElseThrow(() -> new EntityNotFoundException(Position.class, id));
//        } catch (EntityNotFoundException e) {
//            log.error("Position not found");
//            throw e;
//        } catch (Exception e) {
//            log.error("An error occurred during deleting the position");
//            throw e;
//        }
//        return position;
//    }
}
