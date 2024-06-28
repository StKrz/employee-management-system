package gr.aueb.cf.employee.service;

import gr.aueb.cf.employee.dto.PositionInsertDTO;
import gr.aueb.cf.employee.dto.PositionUpdateDTO;
import gr.aueb.cf.employee.model.Position;
import gr.aueb.cf.employee.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.employee.service.exceptions.PositionAlreadyExistException;
import gr.aueb.cf.employee.service.exceptions.PositionNameNotFoundException;

import java.util.List;

public interface IPositionService {
    Position insertPosition(PositionInsertDTO dto) throws PositionAlreadyExistException, EntityNotFoundException;
    List<Position> getAllPositions();
//    Position updatePosition(PositionUpdateDTO dto) throws EntityNotFoundException, PositionAlreadyExistException;
//    Position deletePosition(Long id) throws EntityNotFoundException;
//    Position getPositionByName(String positionName) throws EntityNotFoundException, PositionNameNotFoundException;
//    Position getPositionById(Long id) throws EntityNotFoundException;
}

