package gr.aueb.cf.employee.controller.admincontrollers;

import gr.aueb.cf.employee.dto.PositionInsertDTO;
import gr.aueb.cf.employee.model.Position;
import gr.aueb.cf.employee.service.IPositionService;
import gr.aueb.cf.employee.service.exceptions.PositionAlreadyExistException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@Slf4j
public class PositionController {
    private final IPositionService positionService;

    @Autowired
    public PositionController(IPositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping("/positions/register")
    public ResponseEntity<?> createPosition(@Valid @ModelAttribute("position") PositionInsertDTO positionInsertDTO) {
        try {
            Position newPosition = positionService.insertPosition(positionInsertDTO);
            return new ResponseEntity<>(newPosition, HttpStatus.CREATED);
        } catch (PositionAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Position already exists."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred during position registration."));
        }
    }

//    @PutMapping("/update")
//    public ResponseEntity<Position> updatePosition(@RequestBody PositionUpdateDTO positionUpdateDTO) {
//        try {
//            Position updatedPosition = positionService.updatePosition(positionUpdateDTO);
//            return new ResponseEntity<>(updatedPosition, HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(404).body(null); // Not Found
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null); // Internal Server Error
//        }
//    }

//    @DeleteMapping("/positions/delete/{id}")
//    public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
//        try {
//            positionService.deletePosition(id);
//            return ResponseEntity.noContent().build();
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(404).build(); // Not Found
//        } catch (Exception e) {
//            return ResponseEntity.status(500).build(); // Internal Server Error
//        }
//    }

//    @GetMapping("/{positionName}")
//    public ResponseEntity<Position> getPositionByPositionName(@PathVariable String positionName) {
//        log.info("Received request to get position by name: " + positionName);
//        try {
//            Position position = positionService.getPositionByName(positionName);
//            log.info("Position found: " + position);
//            return new ResponseEntity<>(position, HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//            log.error("Position not found with name: " + positionName, e);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            log.error("An error occurred while fetching the position with name: " + positionName, e);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/id/{id}")
//    public ResponseEntity<Position> getPositionById(@PathVariable Long id) {
//        log.info("Received request to get position by id: " + id);
//        try {
//            Position position = positionService.getPositionById(id);
//            log.info("Position found: " + position);
//            return new ResponseEntity<>(position, HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//            log.error("Position not found with id: " + id, e);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            log.error("An error occurred while fetching the position with id: " + id, e);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/positions/all")
//    public ResponseEntity<List<Position>> getAllPositions() {
//        try {
//            List<Position> positions = positionService.getAllPositions();
//            return new ResponseEntity<>(positions, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}

