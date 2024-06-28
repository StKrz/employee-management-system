package gr.aueb.cf.employee.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PositionInsertDTO {
    @NotEmpty(message = "Position name field cannot be empty")
    private String positionName;
}
